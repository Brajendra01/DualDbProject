package com.TwoDBDemo.onloadDB.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component; 
import com.TwoDBDemo.dao.ProdDaoImpl;
import com.TwoDBDemo.model.prod.Product; 

@Component
public class ProductOnload implements ApplicationRunner{
	
	@Autowired
	ProdDaoImpl prodDao; 
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Product prod = prodDao.getProduct(3);  
		if (prod != null) {
			System.out.println(">>>>>>>>>>>>> Product details :::  id: " + prod.getId() + "   name: " + prod.getName()
					+ "  email: " + prod.getPrice());
		}
		  
	}

}
 