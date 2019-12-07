package com.trilogyed.AdminAPIService.model;




import java.util.Objects;


public class Product {

    private int product_id ;
    private String product_name ;
    private String product_description ;
    private double list_price ;
    private double unit_cost ;
    private int inventory ;


    public Product() {
    }

    public Product(String product_name, String product_description, double list_price, double unit_cost, int inventory) {
        this.product_name = product_name;
        this.product_description = product_description;
        this.list_price = list_price;
        this.unit_cost = unit_cost;
        this.inventory = inventory;
    }
    public Product(int product_id, String product_name, String product_description, double list_price, double unit_cost, int inventory) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.list_price = list_price;
        this.unit_cost = unit_cost;
        this.inventory = inventory;
    }



    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    public double getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(double unit_cost) {
        this.unit_cost = unit_cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return product_id == product.product_id &&
                Double.compare(product.list_price, list_price) == 0 &&
                Double.compare(product.unit_cost, unit_cost) == 0 &&
                inventory == product.inventory &&
                product_name.equals(product.product_name) &&
                product_description.equals(product.product_description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, product_name, product_description, list_price, unit_cost, inventory);
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", list_price=" + list_price +
                ", unit_cost=" + unit_cost +
                ", inventory=" + inventory +
                '}';
    }
}
