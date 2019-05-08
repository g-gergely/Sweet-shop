package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {

    @Test
    public void testAddingSupplier(){
        SupplierDao supplierDao = new SupplierDaoJdbc();
        Supplier supplier1 =new Supplier("Candy", "All kinds of fine dark and milk chocolate.");

        supplierDao.add(supplier1);
    }


   @Test
    public void testRemovingSupplier(){
        SupplierDao supplierDao = new SupplierDaoJdbc();
        supplierDao.remove(1);
    }



}