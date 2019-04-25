package com.codecool.shop.model.order;


import com.codecool.shop.model.Product;

public class LineItem {
    private Product product;
    private int quantity;

    public float getPrice() {
        return product.getDefaultPrice();
    }

    public float getTotalPrice() {
        return product.getDefaultPrice() * quantity;
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
    }

}
