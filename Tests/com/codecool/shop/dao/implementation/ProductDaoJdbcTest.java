package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {

    @Test
    public void testAddProductToDatabase(){
        ProductDao productDao = new ProductDaoJdbc();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product product1 = new Product("Magic Cake", 10, "USD","" +
                "True magical experience",magical, choco);

        productDao.add(product1);
    }

}