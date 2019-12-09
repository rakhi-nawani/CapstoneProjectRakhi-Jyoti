package com.trilogyed.retailedgeservice.domain;

import java.math.BigDecimal;
import java.util.Objects;


public class Item {

    private int invoiceItemId ;
    private int  invoiceId;
    private int  productId ;
    private int  quantity;
    private BigDecimal unitPrice;

    public Item() {
    }

    public Item(int invoiceItemId, int invoiceId, int productId, int quantity, BigDecimal unitPrice) {
        this.invoiceItemId = invoiceItemId;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Item(int invoiceId, int productId, int quantity, BigDecimal unitPrice) {
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = BigDecimal.valueOf(unitPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return invoiceItemId == item.invoiceItemId &&
                invoiceId == item.invoiceId &&
                productId == item.productId &&
                quantity == item.quantity &&
                Objects.equals(unitPrice, item.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, invoiceId, productId, quantity, unitPrice);
    }

    @Override
    public String toString() {
        return "Item{" +
                "invoiceItemId=" + invoiceItemId +
                ", invoiceId=" + invoiceId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
