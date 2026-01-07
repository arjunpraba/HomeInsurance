package com.botree.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.Policy;
import com.botree.model.PolicyClaim;
import com.botree.model.Quote;
import com.botree.model.User;

@Repository
public class RetriveDAO {

	@Autowired
	SessionFactory sf;

	public List<Quote> getQuotes(User user) {
	    var session = sf.openSession();
	    var query = session.createQuery(
	        "select q from Quote q where q.user = :user",
	        Quote.class
	    );
	    query.setParameter("user", user);
	    return query.getResultList();
	}


	public List<Policy> getAllPolicies(User user2) {

	    User user = user2;
	    System.out.println(user);

	    var session = sf.openSession();

	    var query = session.createQuery(
	        "SELECT p FROM Policy p WHERE p.user = :user",
	        Policy.class
	    );

	    query.setParameter("user", user);

	    return query.getResultList();
	}


	public PolicyClaim updatepolicyclaim(Policy policy) {
		// TODO Auto-generated method stub
		    var session = sf.openSession();
		    var tx = session.beginTransaction();

		    try {

		        // Fetch PolicyClaim using Quote (or policyKey if you have it)
		        PolicyClaim claim = session.createQuery(
		                "FROM PolicyClaim pc WHERE pc.quote = :quote",
		                PolicyClaim.class)
		                .setParameter("quote", policy.getQuote())
		                .uniqueResult();

		        if (claim == null) {
		            throw new RuntimeException("PolicyClaim not found for Policy ID: " + policy.getPolicyId());
		        }

		        // 1. Set start date as current date
		        LocalDate startDate = LocalDate.now();
		        claim.setPolicyStartDate(startDate);

		        // 2. Calculate end date using policy term (years)
		        Integer term = claim.getPolicyTerm();
		        if (term == null || term <= 0) {
		            throw new RuntimeException("Invalid policy term");
		        }

		        LocalDate endDate = startDate.plusYears(term);
		        claim.setPolicyEndDate(endDate);

		        // 3. Update status
		        claim.setPolicyStatus("Active");

		        // 4. Persist changes
		        session.merge(claim);

		        tx.commit();
		        return claim;

		    } catch (Exception e) {
		        if (tx != null) tx.rollback();
		        throw e;
		    } finally {
		        session.close();
		    }
		
	}

	
	
}
