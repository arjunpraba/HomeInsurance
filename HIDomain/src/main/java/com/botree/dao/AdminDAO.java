package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.botree.model.Policy;
import com.botree.model.PolicyClaim;

@Repository
@Transactional
public class AdminDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /* =====================================
       FETCH ALL POLICIES
       ===================================== */

    public List<Policy> getPolicies() {

        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        "SELECT p FROM Policy p",
                        Policy.class
                )
                .getResultList();
    }

    /* =====================================
       FETCH ALL POLICY CLAIMS
       ===================================== */

    public List<PolicyClaim> getPolicyClaims() {

        return sessionFactory
                .getCurrentSession()
                .createQuery(
                        "SELECT pc FROM PolicyClaim pc",
                        PolicyClaim.class
                )
                .getResultList();
    }
}

