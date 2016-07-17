package com.lukas.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Lukas on 7/13/2016.
 */
public class EMFactory {

    private static EMFactory emFactory = new EMFactory( );

    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    private EMFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        em = entityManagerFactory.createEntityManager();

    }

    public static EMFactory getInstance() {
        return emFactory;
    }

    public EntityManager getEM() {
        return em;
    }

}
