package com.lukas.dao;


import com.lukas.model.entities.Purchase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PurchaseDAOJpa {

    private EntityManager em;

    public PurchaseDAOJpa(EntityManager em) {
        this.em = em;
    }

    public void addPurchaseToDatabase(Purchase purchase) {
        em.getTransaction().begin();
        em.persist(purchase);
        em.getTransaction().commit();
    }


    public void addWithdrawalToDatabase(Double withdrawal) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        com.lukas.model.entities.Purchase purchase1 = new com.lukas.model.entities.Purchase();

        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        String date = sdf.format(dt);
        String time = stf.format(dt);


        purchase1.setDate(date);
        purchase1.setTime(time);
        purchase1.setType("W");
        purchase1.setSum(-withdrawal);

        em.persist(purchase1);
        em.getTransaction().commit();

        em.close();
        entityManagerFactory.close();
    }

    public Double getCurrentBalance() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();

        String sql = "SELECT SUM(p.Sum) FROM Purchase p";
        Query q = em.createQuery(sql);

        return (Double) q.getSingleResult();

    }

    public Double getBalanceByDate(String date) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();

        TypedQuery<Double> query = em.createQuery(
                "SELECT SUM(p.Sum) FROM Purchase p where p.Date <= :date", Double.class);
        System.out.println(query.setParameter("date", date).getSingleResult());
        return query.setParameter("date", date).getSingleResult();

    }
}


