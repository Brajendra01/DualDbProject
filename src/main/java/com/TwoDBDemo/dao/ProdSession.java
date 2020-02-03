package com.TwoDBDemo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional("prodTranxManager")
public class ProdSession {

	@Autowired
	@Qualifier("prodEntityManager")
    private EntityManager prodEntityManager;

    protected Session getSession() {
        return prodEntityManager.unwrap(Session.class);
    }
}
