package com.TwoDBDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service; 
import com.TwoDBDemo.dao.ProdDaoImpl;
import com.TwoDBDemo.model.prod.Product;

@Service
public class ProductService {

	@Autowired
	ProdDaoImpl prodDao;
	
	public Product getProduct(int id) {
		Product product=prodDao.getProduct(id); 
		return product;  
	}
}
