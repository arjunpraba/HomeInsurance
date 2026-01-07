package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.Policy;
import com.botree.model.PolicyClaim;

@Repository
public class AdminDAO {
	
	@Autowired
	SessionFactory sf;

	public List<Policy> getPolicies() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select * from policy",Policy.class);
		return query.getResultList();
	}

	public List<PolicyClaim> getPolicyClaims() {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.createNativeQuery("select * from PolicyClaim",PolicyClaim.class);
		return query.getResultList();
	}

	
}
