package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier grannys = new Supplier("Granny's", "Traditional, home-made biscuits");
        supplierDataStore.add(grannys);
        Supplier cakeWonders = new Supplier("Cake Wonders", "A wide variety of delicious cakes.");
        supplierDataStore.add(cakeWonders);
        Supplier sweeterSweets = new Supplier("Sweeter Sweets", "Candies - not only for children!");
        supplierDataStore.add(sweeterSweets);
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        supplierDataStore.add(choco);
        Supplier sweetDesire = new Supplier("Sweet Desire", "Special sweets for special occasions.");
        supplierDataStore.add(sweetDesire);

        //setting up a new product category
        ProductCategory biscuit = new ProductCategory("Biscuit", "Pastry", "Various types of sweet baked cakes.");
        productCategoryDataStore.add(biscuit);
        ProductCategory cake = new ProductCategory("Cake", "Pastry", "Various types of sweet baked cakes.");
        productCategoryDataStore.add(cake);
        ProductCategory cupcake = new ProductCategory("Cupcake", "Pastry", "Various types of sweet baked cakes.");
        productCategoryDataStore.add(cupcake);
        ProductCategory candy = new ProductCategory("Candy", "Sweets", "Sweet, sugary food (chocolate or candies).");
        productCategoryDataStore.add(candy);
        ProductCategory chocolate = new ProductCategory("Chocolate", "Sweets", "Sweet, sugary food (chocolate or candies).");
        productCategoryDataStore.add(chocolate);

        //setting up products and printing it
        productDataStore.add(new Product("Crispy cookies", 9.9f, "USD", "Description comes here.", biscuit, grannys));
        productDataStore.add(new Product("Macaroons", 7, "USD", "Description comes here.", biscuit, grannys));
        productDataStore.add(new Product("Pancake with honey", 18, "USD", "Description comes here.", biscuit, grannys));
        productDataStore.add(new Product("Biscuit Collection", 22, "USD", "Description comes here.", biscuit, grannys));
        productDataStore.add(new Product("Cheesecake", 30, "USD", "Description comes here.", cake, cakeWonders));
        productDataStore.add(new Product("Chocolate cake", 32, "USD", "Description comes here.", cake, cakeWonders));
        productDataStore.add(new Product("Candy-pops", 3.5f, "USD", "Description comes here.", candy, sweetDesire));
        productDataStore.add(new Product("Fruit candies", 2.2f, "USD", "Description comes here.", candy, sweeterSweets));
        productDataStore.add(new Product("Rubber bear fruit gums", 2.7f, "USD", "Description comes here.", candy, sweeterSweets));
        productDataStore.add(new Product("Gummy worms", 2.5f, "USD", "Description comes here.", candy, sweeterSweets));
        productDataStore.add(new Product("Chocolate selection", 15.9f, "USD", "Description comes here.", chocolate, sweetDesire));
        productDataStore.add(new Product("Heart-shape chocolate selection", 17.7f, "USD", "Description comes here.", chocolate, sweetDesire));
        productDataStore.add(new Product("Dark chocolate with coffee", 10.9f, "USD", "Description comes here.", chocolate, choco));
        productDataStore.add(new Product("Milk chocolate with hazelnut", 8.9f, "USD", "Description comes here.", chocolate, choco));
        productDataStore.add(new Product("Dark chocolate with pistachio", 9.8f, "USD", "Description comes here.", chocolate, choco));
        productDataStore.add(new Product("Milk chocolate with raspberry", 7.9f, "USD", "Description comes here.", chocolate, choco));
        productDataStore.add(new Product("Blueberry cupcake", 12, "USD", "Description comes here.", cupcake, sweetDesire));
        productDataStore.add(new Product("Chocolate cupcake", 11, "USD", "Description comes here.", cupcake, sweetDesire));
        productDataStore.add(new Product("Vanilla cupcake", 10, "USD", "Description comes here.", cupcake, sweetDesire));
    }
}
