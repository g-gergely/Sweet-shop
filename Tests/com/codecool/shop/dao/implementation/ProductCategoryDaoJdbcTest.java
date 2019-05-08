package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJdbcTest {
    private ProductCategory category1 = new ProductCategory("name1","dep1","desc1");
    private ProductCategory category2 = new ProductCategory("name2","dep2","desc2");
    private ProductCategory category3 = new ProductCategory("name3","dep3","desc3");

    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc();
    private List<ProductCategory> categories = new ArrayList<>(Arrays.asList(category1, category2, category3));

    @BeforeEach
    public void setup() {
        ((ProductCategoryDaoJdbc) productCategoryDao).clearCategoriesTable();
    }

    @Test
    public void testAddToDatabase_FindInDatabase(){
        productCategoryDao.add(category1);
        ProductCategory resultCategory = productCategoryDao.find(1);

        assertEquals(category1.toStringWithoutId(), resultCategory.toStringWithoutId());
    }

    @Test
    public void testRemoveFromDatabase(){
        //Added successfully
        productCategoryDao.add(category1);
        ProductCategory resultCategory = productCategoryDao.find(1);
        assertEquals(category1.toStringWithoutId(), resultCategory.toStringWithoutId());

        //Removed successfully
        productCategoryDao.remove(1);
        assertNull(productCategoryDao.find(1));
    }

    @Test
    public void testGetAllCategoriesCheckEach(){
        addCategoriesToDb();

        List<ProductCategory> resultCategories = productCategoryDao.getAll();
        for (ProductCategory category : categories) {
            boolean categoryFound = false;
            for (ProductCategory resultCategory : resultCategories) {
                boolean doesMatch = resultCategory.toStringWithoutId().equals(category.toStringWithoutId());

                if (!categoryFound && doesMatch) {
                    categoryFound = true;
                    break;
                }
            }
            assertTrue(categoryFound);
        }

    }

    @Test
    public void testGetAllCategoriesCheckSize(){
        addCategoriesToDb();
        List<ProductCategory> resultCategories = productCategoryDao.getAll();

        assertEquals(categories.size(), resultCategories.size());
    }

    private void addCategoriesToDb() {
        for (ProductCategory category : categories) {
            productCategoryDao.add(category);
        }
    }
}