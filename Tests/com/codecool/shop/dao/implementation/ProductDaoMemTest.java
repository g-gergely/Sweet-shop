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

    @Test
    public void testIsHandlesAddedId(){
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product product1 = new Product("Magic Cake", 10, "USD","" +
                "True magical experience",magical, choco);
        Product product2 = new Product("Magic Jumm",50,"USD","",magical,
                choco);

        productDao.add(product1);
        productDao.add(product2);

        assertEquals(product1.getId(), product2.getId()-1);
    }

    @Test
    public void testIsAddProductToMemory(){
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product product1 = new Product("Magic Cake", 10, "USD","" +
                "True magical experience",magical, choco);

        productDao.add(product1);

        assertTrue(productDao.getAll().contains(product1));
    }

    @Test
    public void testIsAddHandlesNull(){
        ProductDao productDao = ProductDaoMem.getInstance();
        Product product = null;

        assertThrows(NullPointerException.class,()->productDao.add(product));
    }

    @Test
    public void testIsFindReturnsNull() {
        int id = -1;
        ProductDao productDao = ProductDaoMem.getInstance();
        assertNull(productDao.find(id));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 40, 44, 4444})
    public void testIsFindReturnsNullForNonExistentNumbers(int num) {
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product product1 = new Product("Magic Cake", 10, "USD","" +
                "True magical experience",magical, choco);

        productDao.add(product1);

        int overLoadedId = product1.getId()+num;
        assertNull(productDao.find(overLoadedId));
    }

    @Test
    public void testFindsGivenProduct() {
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product product1 = new Product("Magic Cake", 10, "USD","" +
                "True magical experience",magical, choco);

        productDao.add(product1);

        assertEquals( product1, productDao.find(product1.getId()));

    }

    @Test
    public void testRemovesProduct(){
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product product1 = new Product("Magic Cake", 10, "USD","" +
                "True magical experience",magical, choco);

        productDao.add(product1);
        productDao.remove(product1.getId());

        assertFalse(productDao.getAll().contains(product1));
    }

    @Test
    public void testThrowsErrorWhenDeletingNonExistentProduct(){
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product product1 = new Product("Magic Cake", 10, "USD","" +
                "True magical experience",magical, choco);

        productDao.add(product1);
        assertDoesNotThrow(()->productDao.remove(product1.getId()));
    }

    @Test
    public void testGetsAllProducts(){
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product[] products = new Product[]{new Product("Magic Cake", 10,
                "USD","" +
                "True magical experience",magical, choco), new Product("Magic Surprise", 10,
                "USD","" +
                "True magical experience",magical, choco), new Product("Magic Pie", 10,
                "USD","" +
                "True magical experience",magical, choco), new Product("Magic Dog Poop", 10,
                "USD","" +
                "True magical experience",magical, choco)};

        for(Product product: products){
            productDao.add(product);
        }

        for (Product product: products){
            assertTrue(productDao.getAll().contains(product));
        }
    }

    @Test
    public void testIsCompareMemorySizeWithAddedProductNumber(){
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Product[] products = new Product[]{new Product("Magic Cake", 10,
                "USD","" +
                "True magical experience",magical, choco), new Product("Magic Surprise", 10,
                "USD","" +
                "True magical experience",magical, choco), new Product("Magic Pie", 10,
                "USD","" +
                "True magical experience",magical, choco), new Product("Magic Dog Poop", 10,
                "USD","" +
                "True magical experience",magical, choco)};

        for(Product product: products){
            productDao.add(product);
        }

        assertEquals(products.length, productDao.getAll().size());
    }

    @Test
    public void testGivesBackAllProductBySupplier(){
        int numOfProducts =4;
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");
        Supplier snoopy = new Supplier("Snoop Dog", "All kinds of magical stuff man!");

        for (int i=0; i< numOfProducts; i++){
            productDao.add(new Product("Magic Cake", 10,
                    "USD","" +
                    "True magical experience",magical, choco));
        }

        productDao.add(new Product("Magic Cake", 10,
                "USD","" +
                "True magical experience",magical, snoopy));

        assertEquals(numOfProducts, productDao.getBy(choco).size());

    }

    @Test
    public void testGivesBackAllProductsByProductCategory(){
        int numOfProducts =4;
        ProductDao productDao = ProductDaoMem.getInstance();
        ProductCategory magical = new ProductCategory("Magical","Bakery","bake hard");
        ProductCategory superSweet = new ProductCategory("Sweet as love","No data","Yum");
        Supplier choco = new Supplier("Choco", "All kinds of fine dark and milk chocolate.");


        for (int i=0; i< numOfProducts; i++){
            productDao.add(new Product("Magic Cake", 10,
                    "USD","" +
                    "True magical experience",magical, choco));
        }

        productDao.add(new Product("Magic Cake", 10,
                "USD","" +
                "True magical experience",superSweet, choco));

        assertEquals(numOfProducts, productDao.getBy(magical).size());
    }

}