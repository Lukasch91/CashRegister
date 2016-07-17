package com.lukas.dao;


import com.lukas.model.entities.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ItemDAOJpa {

    private EntityManager em;

    public ItemDAOJpa(EntityManager em) {
        this.em = em;
    }

    public Item loadItem(Long itemCode) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Item.class, itemCode);
    }


    public long countItems() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();

        String sql = "SELECT COUNT(i) FROM item i";
        Query q = em.createQuery(sql);

        return (long) q.getSingleResult();
    }


    public Item selectRandomItem() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();


        Query q = em.createQuery("SELECT i.itemCode FROM Item i ORDER BY RAND()");
        q.setMaxResults(1);
        int randomItemCode = (int) q.getSingleResult();
        Item item = em.find(Item.class, randomItemCode);

        return item;
    }


}
