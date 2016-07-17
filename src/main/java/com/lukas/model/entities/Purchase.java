package com.lukas.model.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table (name = "purchase")
@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Date")
    private String Date;

    @Column(name = "Time")
    private String Time;

    @Column(name = "Type")
    private String Type;

    @Column(name = "Sum")
    private Double Sum;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Record> recordList = new ArrayList<>();

    public List<Record> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Double getSum() {
        return Sum;
    }

    public void setSum(Double sum) {
        Sum = sum;
    }

    public void addItemToList(com.lukas.model.entities.Record record) {
        recordList.add(record);
    }

    public Double sumPrices() {
        Double sum = 0.00;
        for (com.lukas.model.entities.Record record : recordList) {
            sum = sum + record.getSum();
        }
        return sum;
    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        Purchase purchase = new Purchase();
        purchase.setSum(10.00);
        purchase.setType("P");
        purchase.setDate("2016-07-14");
        purchase.setTime("15:15:15");

        Record record = new Record();
        record.setItemName("Name");
        record.setItemPrice(10.00);
        record.setSum(20.00);
        record.setNumberOfItems(2);
        record.setPurchase(purchase);

        purchase.getRecordList().add(record);

        em.persist(purchase);
        em.getTransaction().commit();

Purchase purchase1 = em.find(Purchase.class, 1);

   System.out.println("Record size " + purchase1.getRecordList().size());
    }
}

