package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.botree.model.Policy;
import com.botree.model.Quote;

@Repository
@Transactional
public class QuoteDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /* =====================================
       LOOKUP TABLE METHODS (READ ONLY)
       ===================================== */

    public List<String> getresidenceTypes() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery(
                        "SELECT name FROM \"ResidenceType\"",
                        String.class
                )
                .getResultList();
    }

    public List<String> getResidenceUses() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery(
                        "SELECT name FROM \"ResidenceUse\"",
                        String.class
                )
                .getResultList();
    }

    public List<String> getDwellingStyle() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery(
                        "SELECT name FROM \"DwellingStyle\"",
                        String.class
                )
                .getResultList();
    }

    public List<String> getRoodMeterial() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery(
                        "SELECT name FROM \"RoofMeterial\"",
                        String.class
                )
                .getResultList();
    }

    public List<String> getGarageTyps() {
        return sessionFactory
                .getCurrentSession()
                .createNativeQuery(
                        "SELECT name FROM \"GarageType\"",
                        String.class
                )
                .getResultList();
    }

    /* =====================================
       WRITE OPERATIONS
       ===================================== */

    public void generateQuote(Quote quote) {
        sessionFactory
                .getCurrentSession()
                .persist(quote);
    }

    public void saveCoverage(Policy policy) {
        sessionFactory
                .getCurrentSession()
                .persist(policy);
    }
}
