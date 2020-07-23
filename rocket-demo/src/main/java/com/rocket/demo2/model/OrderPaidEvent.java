package com.rocket.demo2.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderPaidEvent implements Serializable {

    public OrderPaidEvent(){}

    public OrderPaidEvent(String orderId, BigDecimal paidMoney){
        this.orderId = orderId;

        this.paidMoney = paidMoney;

    }



    private String orderId;

    private BigDecimal paidMoney;
}
