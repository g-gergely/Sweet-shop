package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {
    private ProductCategory magical = new ProductCategory(1,"Magical","Bakery","bake hard");
    private Supplier choco = new Supplier(1, "Choco", "All kinds of fine dark and milk chocolate.");
    private Product product1 = new Product(1, "Name1", 10, "USD","Desc1", magical, choco);
    private Product product2 = new Product(2, "Name2", 20, "USD", "Desc2", magical, choco);
    private Product product3 = new Product(3, "Name3", 30, "USD", "Desc3", magical, choco);

    private List<Product> products = new ArrayList<>(Arrays.asList(product1, product2, product3));

    private ProductDao productDao = new ProductDaoJdbc();
    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc();
    private SupplierDao supplierDao = new SupplierDaoJdbc();

    @BeforeEach
    public void setup() {
        ((ProductDaoJdbc) productDao).clearProductsTable();
        ((ProductCategoryDaoJdbc) productCategoryDao).clearCategoriesTable();
        ((SupplierDaoJdbc) supplierDao).clearSuppliersTable();
    }

    //todo: there could be a test for no supplier and no category cases.
    @Test
    public void testAddProductToDatabase_FindProduct(){
        supplierDao.add(choco);
        productCategoryDao.add(magical);
        productDao.add(product1);

        Product resultProduct = productDao.find(1);
        assertEquals(product1.toString(), resultProduct.toString());
    }

    @Test
    public void testRemoveProductFromDb(){
        testAddProductToDatabase_FindProduct();

        productDao.remove(1);
        assertNull(productDao.find(1));
    }

    @Test
    public void testGetAllProductsCheckEach(){
        supplierDao.add(choco);
        productCategoryDao.add(magical);
        addProductsToDb();

        List<Product> resultProducts = productDao.getAll();
        for (Product product : products) {
            boolean productFound = false;
            for (Product resultProduct : resultProducts) {
                boolean doesMatch = resultProduct.toString().equals(product.toString());

                if (!productFound && doesMatch) {
                    productFound = true;
                    break;
                }
            }
            assertTrue(productFound);
        }

    }

    private void addProductsToDb() {
        for (Product product : products) {
            productDao.add(product);
        }
    }
}