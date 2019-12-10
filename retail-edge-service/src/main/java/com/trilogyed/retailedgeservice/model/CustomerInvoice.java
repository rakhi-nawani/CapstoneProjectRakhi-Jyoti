package com.trilogyed.retailedgeservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.trilogyed.retailedgeservice.domain.Item;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CustomerInvoice {

    private int invoiceId ;
    private int customerId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd")
    private LocalDate purchaseDate;
    private List<Item> Items;
    private int points;
    private BigDecimal orderTotal;

    public CustomerInvoice() {
    }

    public CustomerInvoice(int customerId, LocalDate purchaseDate, List<Item> items) {
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
        Items = items;
    }

    public CustomerInvoice(int invoiceId, int customerId, LocalDate purchaseDate, List<Item> items, int points, BigDecimal orderTotal) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
        Items = items;
        this.points = points;
        this.orderTotal = orderTotal;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        Items = items;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInvoice that = (CustomerInvoice) o;
        return invoiceId == that.invoiceId &&
                customerId == that.customerId &&
                points == that.points &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(Items, that.Items) &&
                Objects.equals(orderTotal, that.orderTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, purchaseDate, Items, points, orderTotal);
    }

    @Override
    public String toString() {
        return "CustomerInvoice{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                ", Items=" + Items +
                ", points=" + points +
                ", orderTotal=" + orderTotal +
                '}';
    }
}

