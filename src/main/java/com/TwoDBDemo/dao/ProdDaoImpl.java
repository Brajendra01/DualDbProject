package com.TwoDBDemo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.TwoDBDemo.model.prod.Product;

@Repository
@Transactional("prodTranxManager")
public class ProdDaoImpl extends ProdSession{

	public void saveProd(Product product) {
		getSession().save(product);
	}
	
	public Product getProduct(int id) {
		Product prod=getSession().get(Product.class, id);  
		return prod; 
	}
}
