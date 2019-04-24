package com.codecool.shop.model.order;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<LineItem> lineItems = new ArrayList<>();

    float totalPrice = 0;

    public List<LineItem> getLineItems() {
        return new ArrayList<>(lineItems);
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
        totalPrice += lineItem.getTotalPrice();
    }

    public void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
