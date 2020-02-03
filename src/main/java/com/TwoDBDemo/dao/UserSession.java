package com.TwoDBDemo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional("prodTranxManager")
public class UserSession {

	@Autowired  
	@Qualifier("userEntityManager")
    private EntityManager userEntityManager;

    protected Session getSession() {
        return userEntityManager.unwrap(Session.class);
    }
}
