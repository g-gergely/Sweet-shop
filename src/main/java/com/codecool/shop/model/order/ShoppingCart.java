package com.codecool.shop.model.order;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<LineItem> lineItems = new ArrayList<>();


    public List<LineItem> getLineItems() {
        return new ArrayList<>(lineItems);
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }
}
