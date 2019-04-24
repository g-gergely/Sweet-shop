package com.codecool.shop.model.order;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<LineItem> lineItems = new ArrayList<>();
    private float totalPrice;


    public List<LineItem> getLineItems() {
        return new ArrayList<>(lineItems);
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }

    public float getTotalPrice() {
        totalPrice = 0;
        lineItems.forEach(lineItem -> totalPrice += lineItem.getTotalPrice());
        return totalPrice;
    }
}
