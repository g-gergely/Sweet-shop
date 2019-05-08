package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private static final String DATABASE = System.getenv("POSTGRES_DB_NAME");
    private static final String DB_USER = System.getenv("POSTGRES_DB_USER");
    private static final String DB_PASSWORD = System.getenv("POSTGRES_DB_PASSWORD");

    @Override
    public void add(ProductCategory category) {
        String query = "INSERT INTO categories (name, description, department) VALUES(?,?,?)";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            preparedStatement.setString(3,category.getDepartment());
            preparedStatement.execute();
        }
        catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM categories WHERE id=?";

        ProductCategory productCategory = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String description = resultSet.getString("description");
                productCategory = new ProductCategory(id, name, department, description);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return productCategory;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM categories WHERE id = ?";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        }
        catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM categories";

        List<ProductCategory> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String department = resultSet.getString("department");

                ProductCategory productCategory = new ProductCategory(id, name, department, description);
                categories.add(productCategory);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return categories;
    }

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }

    public void clearCategoriesTable() {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute("TRUNCATE TABLE categories CASCADE; ALTER SEQUENCE categories_id_seq RESTART WITH 1;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
