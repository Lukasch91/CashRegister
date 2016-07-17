package com.lukas.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;

@Entity(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemCode;
    private String itemName;
    private Double itemPrice;

    @ManyToOne()
    @JoinColumn(name = "discountId")
    private Discount discount;

    public Item () {

    }



    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public void setItemCode(Integer itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }


    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;

    }

    public Double getItemPriceWithDiscount() {
        if (discount == null) {
            return itemPrice;
        }
        Double priceWithDiscount = discount.calculateDiscount(itemPrice);
        if (priceWithDiscount < 0) {
            throw new RuntimeException("Item price under 0.00");
        }
        return priceWithDiscount;
    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();
        Item item = em.find(Item.class, 1);
System.out.println(item.getDiscount().getId());



    }
}
