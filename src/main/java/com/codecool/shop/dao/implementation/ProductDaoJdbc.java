package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private static final String DATABASE = System.getenv("POSTGRES_DB_NAME");
    private static final String DB_USER = System.getenv("POSTGRES_DB_USER");
    private static final String DB_PASSWORD = System.getenv("POSTGRES_DB_PASSWORD");

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
            preparedStatement.setInt(5,product.getSupplier().getId());
            preparedStatement.setInt(6,product.getProductCategory().getId());
            preparedStatement.execute();
        }
        catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public Product find(int id) {
        String query = "SELECT FROM products WHERE id=?";

        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Float defaulPrice = resultSet.getFloat("default_price");
                String currency = resultSet.getString("currency");
                int supplierId = resultSet.getInt("supplier_id");
                Supplier supplier = null;
                int categoryId = resultSet.getInt("category_id");
                ProductCategory productCategory = null;

                product = new Product(name, defaulPrice, currency, description, productCategory, supplier);
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
}

