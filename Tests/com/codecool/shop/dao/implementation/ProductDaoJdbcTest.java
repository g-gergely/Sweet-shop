package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {
    private ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
    private Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
    private Product product1 = new Product("Magic Cake", 10, "USD","" +
            "True magical experience",magical, choco);

    @Test
    public void testAddProductToDatabase(){
        ProductDao productDao = new ProductDaoJdbc();
        productDao.add(product1);
    }

    @Test
    public void testFindProduct(){
        //todo: Solve the supplier and product category problem for getting product
        Product product1 = new Product("Magic Cake", 10, "USD","" +
                "True magical experience");

        // add product to database
        ProductDao productDao = new ProductDaoJdbc();
        productDao.add(product1);
        //get product from database
        Product resultProduct =productDao.find(product1.getId());
        //assert equals
        assertEquals(product1, resultProduct);
    }

}