package com.trilogyed.retailedgeservice.domain;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

public class Order {

    int quantity;
    int customerId;
    int productId;

    public Order(int quantity, int customerId, int productId) {
        this.quantity = quantity;
        this.customerId = customerId;
        this.productId = productId;
    }



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return quantity == order.quantity &&
                customerId == order.customerId &&
                productId == order.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, customerId, productId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "quantity=" + quantity +
                ", customerId=" + customerId +
                ", productId=" + productId +
                '}';
    }
}
