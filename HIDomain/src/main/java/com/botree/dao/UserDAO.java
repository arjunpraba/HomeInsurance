package com.botree.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.User;

@Repository
public class UserDAO {

	@Autowired
	SessionFactory sf;

	public void register(User u) {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var trx=session.beginTransaction();
		session.save(u);
		trx.commit();
	}

	public boolean authenticate(User u) {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		var query=session.get(User.class,u.getUserId());
		if(query.getPassword().equals(u.getPassword())){
			return true;
		}else {
			return false;
		}
	}

	public String getUserType(User u) {
		// TODO Auto-generated method stub
		
		var session=sf.openSession();
		var user=session.get(User.class,u.getUserId());
			
		
		return user.getFirstName();
	}

	public User getUser(String userId) {
		// TODO Auto-generated method stub
		var session=sf.openSession();
		return session.get(User.class,userId);			
	}
	
	
}
