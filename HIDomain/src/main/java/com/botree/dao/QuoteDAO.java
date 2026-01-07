package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.Policy;
import com.botree.model.Quote;

@Repository
public class QuoteDAO {
	
	@Autowired
	SessionFactory sf;

	public List<String> getresidenceTypes() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select name from ResidenceType",String.class);
		return query.getResultList();
	}

	public List<String> getResidenceUses() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select name from ResidenceUse",String.class);
		return query.getResultList();
	}

	public List<String> getDwellingStyle() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select name from DwellingStyle",String.class);
		return query.getResultList();
	}

	public List<String> getRoodMeterial() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select name from RoofMeterial",String.class);
		return query.getResultList();
	}

	public List<String> getGarageTyps() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select name from GarageType",String.class);
		return query.getResultList();
	}

	public void generateQuote(Quote quote) {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var tx=session.beginTransaction();
		session.save(quote);
		tx.commit();
	}

	public void saveCoverage(Policy p) {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var tx=session.beginTransaction();
		session.save(p);
		tx.commit();	
	}

}
