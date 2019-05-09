package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends BaseModel {
    private String department;
    private List<Product> products;

    public ProductCategory(String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.products = new ArrayList<>();
    }

    public ProductCategory(int id, String name, String department, String description) {
        super(name, description);
        this.department = department;
        this.products = new ArrayList<>();
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public ProductCategory getDefaultCategory() {
        return new ProductCategory(-1, "No category", "No department", "No description");
    }

    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }

    public String toStringWithoutId() {
        return String.format(
                        "name: %1$s, " +
                        "department: %2$s, " +
                        "description: %3$s",
                this.name,
                this.department,
                this.description);
    }
}