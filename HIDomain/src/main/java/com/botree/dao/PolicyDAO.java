package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.botree.model.PolicyClaim;
import com.botree.model.PolicyDetails;

@Repository
@Transactional
public class PolicyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /* =====================================
       LOOKUP TABLE METHODS
       ===================================== */

    public List<String> getCardTypes() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery(
                        "SELECT name FROM \"CardTypes\"",
                        String.class
                )
                .getResultList();
    }

    public List<String> getVendors() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery(
                        "SELECT name FROM \"Vendors\"",
                        String.class
                )
                .getResultList();
    }

    public List<String> getCardBrands() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery(
                        "SELECT name FROM \"CardBrands\"",
                        String.class
                )
                .getResultList();
    }

    /* =====================================
       WRITE OPERATIONS
       ===================================== */

    public void savePolicyDetails(PolicyDetails policyDetails) {
        sessionFactory
                .getCurrentSession()
                .persist(policyDetails);
    }

    public void savePolicyClaim(PolicyClaim policyClaim) {
        sessionFactory
                .getCurrentSession()
                .persist(policyClaim);
    }
}

