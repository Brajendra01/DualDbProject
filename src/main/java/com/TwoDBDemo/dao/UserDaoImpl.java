package com.TwoDBDemo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.TwoDBDemo.model.user.User;

@Repository
@Transactional("userTranxManager")
public class UserDaoImpl extends UserSession{

	public void saveUser(User user) {
		getSession().save(user);
	}
	
	public User getUser(int id) {
		User user=getSession().get(User.class, id);   
		return user;
	}
}
