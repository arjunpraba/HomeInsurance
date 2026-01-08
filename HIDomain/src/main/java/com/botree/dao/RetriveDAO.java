package com.botree.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.botree.model.Policy;
import com.botree.model.PolicyClaim;
import com.botree.model.Quote;
import com.botree.model.User;

@Repository
@Transactional
public class RetriveDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /* =====================================
       FETCH QUOTES FOR A USER
       ===================================== */

    public List<Quote> getQuotes(User user) {

        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        "SELECT q FROM Quote q WHERE q.user = :user",
                        Quote.class
                )
                .setParameter("user", user)
                .getResultList();
    }

    /* =====================================
       FETCH POLICIES FOR A USER
       ===================================== */

    public List<Policy> getAllPolicies(User user) {

        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        "SELECT p FROM Policy p WHERE p.user = :user",
                        Policy.class
                )
                .setParameter("user", user)
                .getResultList();
    }

    /* =====================================
       ACTIVATE POLICY (UPDATE POLICY CLAIM)
       ===================================== */

    public PolicyClaim updatepolicyclaim(Policy policy) {

        // Fetch PolicyClaim using associated Quote
        PolicyClaim claim = sessionFactory
                .getCurrentSession()
                .createQuery(
                        "SELECT pc FROM PolicyClaim pc WHERE pc.quote = :quote",
                        PolicyClaim.class
                )
                .setParameter("quote", policy.getQuote())
                .uniqueResult();

        if (claim == null) {
            throw new RuntimeException(
                "PolicyClaim not found for Policy ID: " + policy.getPolicyId()
            );
        }

        // 1. Policy start date = today
        LocalDate startDate = LocalDate.now();
        claim.setPolicyStartDate(startDate);

        // 2. Policy end date = start date + term
        Integer term = claim.getPolicyTerm();
        if (term == null || term <= 0) {
            throw new RuntimeException("Invalid policy term");
        }

        LocalDate endDate = startDate.plusYears(term);
        claim.setPolicyEndDate(endDate);

        // 3. Update status
        claim.setPolicyStatus("Active");

        // 4. Persist changes (managed entity → no merge needed)
        sessionFactory
                .getCurrentSession()
                .update(claim);

        return claim;
    }
}

