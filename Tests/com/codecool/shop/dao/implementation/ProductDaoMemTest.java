package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoMemTest {
    private ProductDao productDao = ProductDaoMem.getInstance();
    private ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
    private Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
    private Product product4 = new Product("Magic Dog Poop", 10,
            "USD", "" +
            "True magical experience", magical, choco);
    private Product product3 = new Product("Magic Pie", 10,
            "USD", "" +
            "True magical experience", magical, choco);
    private Product product1 = new Product("Magic Cake", 10, "USD","" +
            "True magical experience",magical, choco);
    private Product product2 = new Product("Magic Jumm",50,"USD","",magical,
            choco);

    @Test
    public void testIsHandlesAddedId(){
        productDao.add(product1);
        productDao.add(product2);

        assertEquals(product1.getId(), product2.getId()-1);
    }

    @Test
    public void testIsAddProductToMemory(){
        productDao.add(product1);

        assertTrue(productDao.getAll().contains(product1));
    }

    @Test
    public void testIsAddHandlesNull(){
        Product product = null;

        assertThrows(NullPointerException.class,()->productDao.add(product));
    }

    @Test
    public void testIsFindReturnsNull() {
        assertNull(productDao.find(-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 40, 44, 4444})
    public void testIsFindReturnsNullForNonExistentNumbers(int num) {
        productDao.add(product1);

        int overLoadedId = product1.getId()+num;
        assertNull(productDao.find(overLoadedId));
    }

    @Test
    public void testFindsGivenProduct() {
        productDao.add(product1);

        assertEquals( product1, productDao.find(product1.getId()));
    }

    @Test
    public void testRemovesProduct(){
        productDao.add(product1);
        productDao.remove(product1.getId());

        assertFalse(productDao.getAll().contains(product1));
    }

    @Test
    public void testThrowsErrorWhenDeletingNonExistentProduct(){
        productDao.add(product1);
        assertDoesNotThrow(()->productDao.remove(product1.getId()));
    }

    @Test
    public void testGetsAllProducts(){
        Product[] products = new Product[]{product1, product2, product3, product4};

        for(Product product: products){
            productDao.add(product);
        }

        for (Product product: products){
            assertTrue(productDao.getAll().contains(product));
        }
    }

    @Test
    public void testIsCompareMemorySizeWithAddedProductNumber(){
        ((ProductDaoMem)productDao).clear();
        Product[] products = new Product[]{product1,product2,product3,product4};
        for(Product product: products){
            productDao.add(product);
        }

        assertEquals(products.length, productDao.getAll().size());
    }

    @Test
    public void testGivesBackAllProductBySupplier(){
        Supplier snoopy = new Supplier("Snoop Dog", "All kinds of magical stuff man!");

        Product[] chocoProducts = new Product[]{product1,product2,product3,product4};
        for(Product product: chocoProducts){
            productDao.add(product);
        }

        productDao.add(new Product("Magic Cake", 10,
                "USD","" +
                "True magical experience",magical, snoopy));

        assertEquals(chocoProducts.length, productDao.getBy(choco).size());
    }

    @Test
    public void testGivesBackAllProductsByProductCategory(){
        ProductCategory superSweet = new ProductCategory("Sweet as love","No data","Yum");

        Product[] magicProducts = new Product[]{product1,product2,product3,product4};
        for(Product product: magicProducts){
            productDao.add(product);
        }

        productDao.add(new Product("Magic Cake", 10,
                "USD","" +
                "True magical experience",superSweet, choco));

        assertEquals(magicProducts.length, productDao.getBy(magical).size());
    }

}