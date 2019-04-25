package com.codecool.shop.model.order;


import com.codecool.shop.model.Product;

public class LineItem {
    private Product product;
    private int quantity;
    private float totalPrice;
    private int id;

    public float getPrice() {
        return product.getDefaultPrice();
    }

    public float getTotalPrice() {
        return (float) (Math.round(product.getDefaultPrice() * quantity * 10.0) / 10.0);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return product.getName();
    }

    public LineItem (Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = product.getDefaultPrice() * quantity;
        this.id = (int) (Math.random() * 1000000);
    }

    public int getId() {
        return id;
    }
}
