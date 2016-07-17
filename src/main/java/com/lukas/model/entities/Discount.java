package com.lukas.model.entities;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.Persistence;


@Entity
@Inheritance
@DiscriminatorColumn(name = "typeOfDiscount")
public abstract class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double calculateDiscount(Double price) {
        Double priceWithDiscount = applyDiscount(price);
        if (priceWithDiscount < 0) {
            throw new RuntimeException("Item price under 0.00");
        }
        return priceWithDiscount;


    }

    protected abstract  Double applyDiscount(Double price);


    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        AmountDiscount amountDiscount = new AmountDiscount();

        amountDiscount.setDiscountAmount(10.00);

        em.persist(amountDiscount);

        em.getTransaction().commit();

       Discount result = em.find(Discount.class, 1);

        if (result instanceof AmountDiscount) {
            AmountDiscount amountDisc = (AmountDiscount)result;
            System.out.println(amountDisc.getDiscountAmount());
            System.out.println(amountDisc.getId());

        } else if (result instanceof PercentageDiscount) {
            PercentageDiscount percentageDiscount = (PercentageDiscount)result;
            System.out.println(percentageDiscount.getDiscountAmount());
            System.out.println(percentageDiscount.getId());

        }



    }
}


