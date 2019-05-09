package com.codecool.shop.dao.implementation;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJdbcTest {
    Supplier supplier1 =new Supplier("Candy1", "Making Donnald Trump's hair");
    Supplier supplier2 =new Supplier("Candy2", "Making Donnald Trump's hair");
    Supplier supplier3 =new Supplier("Candy3", "Making Donnald Trump's hair");
    Supplier supplier4 =new Supplier("Candy4", "Making Donnald Trump's hair");
    List<Supplier> suppliers = getSuppliers();
    SupplierDao supplierDao = new SupplierDaoJdbc();


    @BeforeEach
    public void clearSupplierTable(){
        ((SupplierDaoJdbc)supplierDao).clearSuppliersTable();
    }

    @Test
    public void testAddingSupplier(){
        supplierDao.add(supplier1);
    }

    @Test
    public void testFindSupplier(){
        supplierDao.add(supplier1);
        Supplier resultSupplier = supplierDao.find(1);
        assertEquals(supplier1.toStringWithoutId(),resultSupplier.toStringWithoutId());
    }


   @Test
    public void testRemovingSupplier(){
       supplierDao.add(supplier1);
        supplierDao.remove(1);
       assertNotNull(supplierDao.find(1));

    }

    @Test
    public void testIsFindAll(){

        for (Supplier supplier: suppliers){
            supplierDao.add(supplier);
        }

        List<Supplier> returnedSuppliers = supplierDao.getAll();

        assertEquals(suppliers.size(), returnedSuppliers.size());
    }

    private List<Supplier> getSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();               //todo: Make different suppliers

        suppliers.add(supplier1);
        suppliers.add(supplier2);
        suppliers.add(supplier3);
        suppliers.add(supplier4);
        return suppliers;
    }

    @Test
    public void testFindAllAddedSuppliers(){
        int checkedSuppliers =0;


        for (Supplier supplier: suppliers){
            supplierDao.add(supplier);
        }

        List<Supplier> returnedSuppliers = supplierDao.getAll();

        for(Supplier returnedSupplier:returnedSuppliers){
            for(Supplier supplier: suppliers) {
                if (returnedSupplier.toStringWithoutId().equals(supplier.toStringWithoutId())) {
                    checkedSuppliers++;
                }
            }
        }

        assertEquals(suppliers.size(), checkedSuppliers);
    }



}