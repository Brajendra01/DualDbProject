package com.TwoDBDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;  
import com.TwoDBDemo.dao.ProdDaoImpl;
import com.TwoDBDemo.dao.UserDaoImpl;
import com.TwoDBDemo.model.prod.Product;
import com.TwoDBDemo.model.user.User;

@RestController
@RequestMapping("/api/twodb")    
public class DBController {
	
	@Autowired
	UserDaoImpl userDao;
	
	@Autowired
	ProdDaoImpl prodDao;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public void saveUser() {
		User user=new User("Hari", "hari@gmail.com"); 
		userDao.saveUser(user);
		
	}    
	 
	@RequestMapping(value = "/prod", method = RequestMethod.POST)  
	public void saveProd() {
		Product product=new Product("oppo phone", 10000);   
		prodDao.saveProd(product);
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)    
	public User getUser() {
		User user=userDao.getUser(3);   
		return user;    
	}
	
	@RequestMapping(value = "/getProd", method = RequestMethod.POST)    
	public Product getProd() {
		Product product=prodDao.getProduct(4);    
		return product; 
	}
}
