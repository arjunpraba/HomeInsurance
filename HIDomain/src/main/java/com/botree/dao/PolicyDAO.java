package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.PolicyClaim;
import com.botree.model.PolicyDetails;

@Repository
public class PolicyDAO {
	
	@Autowired
	SessionFactory sf;

	public List<String> getCardTypes() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select name from CardTypes",String.class);
		return query.getResultList();
	}

	public List<String> getVendors() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select name from Vendors",String.class);
		return query.getResultList();
	}

	public List<String> getCardBrands() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select name from CardBrands",String.class);
		return query.getResultList();
	}

	public void savePolicyDetails(PolicyDetails pp) {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var tx=session.beginTransaction();
		session.save(pp);
		tx.commit();	
	}

	public void savePolicyClaim(PolicyClaim pc) {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var tx=session.beginTransaction();
		session.save(pc);
		tx.commit();
		
	}

}
