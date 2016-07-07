package com.lukas.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Lukas on 7/7/2016.
 */

    @Entity(name = "Discount")
    public class Discount
    {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Integer discountId;

        @Column(name = "typeOfDiscount")
        private String typeOfDiscount;

        @Column(name = "discountAmount")
        private Double discountAmount;

        public Integer getDiscountId() {
            return discountId;
        }

        public void setDiscountId(Integer discountId) {
            this.discountId = discountId;
        }

        public String getTypeOfDiscount() {
            return typeOfDiscount;
        }

        public void setTypeOfDiscount(String typeOfDiscount) {
            this.typeOfDiscount = typeOfDiscount;
        }

        public Double getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(Double discountAmount) {
            this.discountAmount = discountAmount;
        }
    }
