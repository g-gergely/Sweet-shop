package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoMemTest {

    @Test
    public void testIsAddHandlesId() {
        Supplier supplier1 = new Supplier("Joe", "He is good guy");
        Supplier supplier2 = new Supplier("Marry", "She is not bad");
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        assertEquals(supplier1.getId() + 1, supplier2.getId());
    }

    @Test
    public void testIsAddSupplierToMemory() {
        Supplier supplier1 = new Supplier("Joe", "He is good guy");
        SupplierDao supplierDao = SupplierDaoMem.getInstance();

        supplierDao.add(supplier1);
        assertTrue(supplierDao.getAll().contains(supplier1));

    }

    @Test
    public void testIsAddHandlesNull() {
        Supplier supplier1 = null;
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        assertThrows(NullPointerException.class, () -> supplierDao.add(supplier1));
    }

    @Test
    public void testIsFindReturnsNull() {
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        assertNull(supplierDao.find(-1));

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 50, 40, 99, 1000})
    public void testIsFindReturnsNullForNonExistentNumbers(int num) {
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        Supplier supplier1 = new Supplier("Joe", "He is good guy");

        supplierDao.add(supplier1);
        int higherThenExistedId = supplier1.getId() + num;
        assertNull(supplierDao.find(higherThenExistedId));

    }

    @Test
    public void testIsFindsGivenSupplier() {
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        Supplier supplier1 = new Supplier("Joe", "He is good guy");

        supplierDao.add(supplier1);
        assertEquals(supplier1, supplierDao.find(supplier1.getId()));

    }

    @Test
    public void testIsRemoveSupplier() {
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        Supplier supplier1 = new Supplier("Joe", "He is good guy");
        supplierDao.add(supplier1);
        supplierDao.remove(supplier1.getId());
        assertFalse(supplierDao.getAll().contains(supplier1));
    }

    @Test
    public void testIsRemoveNotGivesErrorForMissingSupplier() {
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        Supplier supplier1 = new Supplier("Joe", "He is good guy");
        supplierDao.add(supplier1);
        supplierDao.remove(supplier1.getId());
        assertDoesNotThrow(() -> supplierDao.remove(supplier1.getId()));
    }

    @Test
    public void testIsGetAllChecksEverySupplierAdded() {
        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        supplierDaoMem.clear();
        Supplier[] suppliers = new Supplier[]{new Supplier("Joe", "He is good guy"),
                new Supplier("Marry", "He is good guy"),
                new Supplier("Kate", "He is good guy"),
                new Supplier("Andris", "He is good guy")};

        for (Supplier supplier : suppliers) {
            supplierDaoMem.add(supplier);
        }

        for (Supplier supplier : suppliers) {
            assertTrue(supplierDaoMem.getAll().contains(supplier));

        }

    }

    @Test
    public void testIsGetAllChecksSize() {
        SupplierDaoMem supplierDaoMem = SupplierDaoMem.getInstance();
        supplierDaoMem.clear();
        Supplier[] suppliers = new Supplier[]{new Supplier("Joe", "He is good guy"),
                new Supplier("Marry", "He is good guy"),
                new Supplier("Kate", "He is good guy"),
                new Supplier("Andris", "He is good guy")};

        for (Supplier supplier : suppliers) {
            supplierDaoMem.add(supplier);
        }
        assertEquals(suppliers.length, supplierDaoMem.getAll().size());
    }


}