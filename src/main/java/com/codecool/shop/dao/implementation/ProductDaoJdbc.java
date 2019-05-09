package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private static final String DATABASE = System.getenv("POSTGRES_DB_NAME");
    private static final String DB_USER = System.getenv("POSTGRES_DB_USER");
    private static final String DB_PASSWORD = System.getenv("POSTGRES_DB_PASSWORD");

    private ProductCategoryDao productCategoryDao = new ProductCategoryDaoJdbc();
    private SupplierDao supplierDao = new SupplierDaoJdbc();

    @Override
    public void add(Product product) {
        String query = "INSERT INTO products ( name, description, default_price, currency, supplier_id, category_id)" +
                "VALUES(?,?,?,?,?,?)";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setFloat(3,product.getDefaultPrice());
            preparedStatement.setString(4,product.getDefaultCurrency().toString());

            int supplierId = (product.getSupplier()==null) ? -1 :  product.getSupplier().getId();
            preparedStatement.setInt(5,supplierId);
            int categoryId = (product.getProductCategory() == null) ? -1 : product.getProductCategory().getId();
            preparedStatement.setInt(6,categoryId);
            preparedStatement.execute();
        }
        catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM products WHERE id=?";

        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Float defaulPrice = resultSet.getFloat("default_price");
                String currency = resultSet.getString("currency");

                ProductCategory productCategory = getCategoryBy(resultSet.getInt("category_id"));
                Supplier supplier = getSupplierBy(resultSet.getInt("supplier_id"));

                product = new Product(id, name, defaulPrice, currency, description, productCategory, supplier);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return product;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }

    public void clearProductsTable() {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute("TRUNCATE TABLE products CASCADE; ALTER SEQUENCE products_id_seq RESTART WITH 1;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductCategory getCategoryBy(int categoryId) {
        ProductCategory productCategory = productCategoryDao.find(categoryId);
        if (productCategory == null) productCategory = productCategory.getDefaultCategory();

        return productCategory;
    }

    public Supplier getSupplierBy(int supplierId) {
        Supplier supplier = supplierDao.find(supplierId);
        if (supplier == null) supplier = supplier.getDefaultSupplier();

        return supplier;
    }
}

