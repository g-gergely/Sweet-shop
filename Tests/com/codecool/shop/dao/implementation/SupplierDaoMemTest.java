package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoMemTest {
    private SupplierDao supplierDao = SupplierDaoMem.getInstance();
    private Supplier supplier1 = new Supplier("Joe", "He is good guy");
    private Supplier supplier2 = new Supplier("Marry", "She is not bad");
    private Supplier supplier3 = new Supplier("Kate", "He is good guy");
    private Supplier supplier4 = new Supplier("Andris", "He is good guy");

    @Test
    public void testIsAddHandlesId() {
        supplierDao.add(supplier1);
        supplierDao.add(supplier2);
        assertEquals(supplier1.getId() + 1, supplier2.getId());
    }

    @Test
    public void testIsAddSupplierToMemory() {
        supplierDao.add(supplier1);
        assertTrue(supplierDao.getAll().contains(supplier1));
    }

    @Test
    public void testIsAddHandlesNull() {
        Supplier supplier1 = null;
        assertThrows(NullPointerException.class, () -> supplierDao.add(supplier1));
    }

    @Test
    public void testIsFindReturnsNull() {
        assertNull(supplierDao.find(-1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 50, 40, 99, 1000})
    public void testIsFindReturnsNullForNonExistentNumbers(int num) {
        supplierDao.add(supplier1);
        int higherThenExistedId = supplier1.getId() + num;

        assertNull(supplierDao.find(higherThenExistedId));
    }

    @Test
    public void testIsFindsGivenSupplier() {
        supplierDao.add(supplier1);
        assertEquals(supplier1, supplierDao.find(supplier1.getId()));
    }

    @Test
    public void testIsRemoveSupplier() {
        supplierDao.add(supplier1);
        supplierDao.remove(supplier1.getId());
        assertFalse(supplierDao.getAll().contains(supplier1));
    }

    @Test
    public void testIsRemoveNotGivesErrorForMissingSupplier() {
        supplierDao.add(supplier1);
        supplierDao.remove(supplier1.getId());
        assertDoesNotThrow(() -> supplierDao.remove(supplier1.getId()));
    }

    @Test
    public void testIsGetAllChecksEverySupplierAdded() {
        ((SupplierDaoMem)supplierDao).clear();
        Supplier[] suppliers = new Supplier[]{supplier1, supplier2, supplier3, supplier4};

        for (Supplier supplier : suppliers) {
            supplierDao.add(supplier);
        }

        for (Supplier supplier : suppliers) {
            assertTrue(supplierDao.getAll().contains(supplier));
        }
    }

    @Test
    public void testIsGetAllChecksSize() {
        ((SupplierDaoMem)supplierDao).clear();
        Supplier[] suppliers = new Supplier[]{supplier1, supplier2, supplier3, supplier4};

        for (Supplier supplier : suppliers) {
            supplierDao.add(supplier);
        }
        assertEquals(suppliers.length, supplierDao.getAll().size());
    }
}