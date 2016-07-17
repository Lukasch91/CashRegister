package com.lukas.model.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("F")
public class PercentageDiscount extends Discount {

    private Double discountAmount;

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public Double applyDiscount(Double price) {
        return price - price * discountAmount / 100;
    }
}

