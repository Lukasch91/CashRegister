package com.lukas.model.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.stream.IntStream;

/**
 * Created by Lukas on 7/6/2016.
 */
public class ItemGenerator {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction userTransaction = em.getTransaction();

        userTransaction.begin();
        for (int i=0; i<=10; i++) {
            Item item = new Item();
            item.setName("Name"+i);
            item.setPrice(15.00);
            em.persist(item);
        }
        userTransaction.commit();

        Item item = em.find(Item.class, 5);
        System.out.print(item.getName());
    }
}
