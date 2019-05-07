package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoMemTest {
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private ProductCategory productCategory1 = new ProductCategory("name 1", "dep 1", "Desc 1");
    private ProductCategory productCategory2 = new ProductCategory("name 2", "dep 2", "Desc 2");
    private ProductCategory productCategory3 = new ProductCategory("name 3", "dep 3", "Desc 3");
    private ProductCategory productCategory4 = new ProductCategory("name 4", "dep 4", "Desc 4");


    @BeforeEach
    public void clearUp() {
        ((ProductCategoryDaoMem) productCategoryDao).clear();
    }

    @Test
    public void testIsAddHandlesId() {
        productCategoryDao.add(productCategory1);
        productCategoryDao.add(productCategory2);
        assertEquals(productCategory1.getId() + 1, productCategory2.getId());
    }

    @Test
    public void testIsAddSupplierToMemory() {
        productCategoryDao.add(productCategory1);
        assertTrue(productCategoryDao.getAll().contains(productCategory1));
    }

    @Test
    public void testIsAddHandlesNull() {
        ProductCategory productCategory = null;
        assertThrows(NullPointerException.class, () -> productCategoryDao.add(productCategory));
    }

    @Test
    public void testIsFindReturnsNull() {
        assertNull(productCategoryDao.find(-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 50, 40, 99, 1000})
    public void testIsFindReturnsNullForNonExistentNumbers(int num) {
        productCategoryDao.add(productCategory1);
        int higherThenExistedId = productCategory1.getId() + num;

        assertNull(productCategoryDao.find(higherThenExistedId));
    }

    @Test
    public void testIsFindsGivenSupplier() {
        productCategoryDao.add(productCategory1);
        assertEquals(productCategory1, productCategoryDao.find(productCategory1.getId()));
    }

    @Test
    public void testIsRemoveSupplier() {
        productCategoryDao.add(productCategory1);
        productCategoryDao.remove(productCategory1.getId());

        assertFalse(productCategoryDao.getAll().contains(productCategory1));
    }

    @Test
    public void testIsRemoveNotGivesErrorForMissingSupplier() {
        productCategoryDao.add(productCategory1);
        productCategoryDao.remove(productCategory1.getId());

        assertDoesNotThrow(() -> productCategoryDao.remove(productCategory1.getId()));
    }

    @Test
    public void testIsGetAllChecksEverySupplierAdded() {
        ProductCategory[] productCategories = new ProductCategory[]{
                productCategory1, productCategory2, productCategory3, productCategory4
        };

        for (ProductCategory productCategory : productCategories) {
            productCategoryDao.add(productCategory);
        }

        for (ProductCategory productCategory : productCategories) {
            assertTrue(productCategoryDao.getAll().contains(productCategory));
        }
    }

    @Test
    public void testIsGetAllChecksSize() {
        ProductCategory[] productCategories = new ProductCategory[]{
                productCategory1, productCategory2, productCategory3, productCategory4
        };

        for (ProductCategory productCategory : productCategories) {
            productCategoryDao.add(productCategory);
        }

        assertEquals(productCategories.length, productCategoryDao.getAll().size());
    }

}