package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJdbcTest {
    private ProductCategory magical = new ProductCategory(1,"Magical","Bakery","bake hard");
    private Supplier choco = new Supplier(1, "Choco", "All kinds of fine dark and milk chocolate.");
    private Product product1 = new Product(1, "Magic Cake", 10, "USD","" +
            "True magical experience",magical, choco);

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

}