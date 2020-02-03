package com.TwoDBDemo.onloadDB.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component; 
import com.TwoDBDemo.dao.UserDaoImpl;
import com.TwoDBDemo.model.user.User;

@Component
public class UserOnload implements ApplicationRunner{

	@Autowired
	UserDaoImpl userDao;
	
	@Override 
	public void run(ApplicationArguments args) throws Exception {
		User user=userDao.getUser(3);
		if(user!=null) {
			System.out.println(">>>>>>>>>>>>> User details :::  id: "+user.getId()+"   name: "+user.getName()+"  email: "+user.getEmail());
		}
	} 

}
