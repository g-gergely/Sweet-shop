package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private static final String DATABASE = System.getenv("POSTGRES_DB_NAME");
    private static final String DB_USER = System.getenv("POSTGRES_DB_USER");
    private static final String DB_PASSWORD = System.getenv("POSTGRES_DB_PASSWORD");

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO suppliers ( name, description)" +
                "VALUES(?,?)";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1,supplier.getName());
            preparedStatement.setString(2,supplier.getDescription());
            preparedStatement.execute();


        }
        catch (SQLException e){
            System.out.println(e.getErrorCode());
        }


    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM suppliers WHERE id=?";

        Supplier supplier = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");

                supplier = new Supplier(id, name, description);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return supplier;
    }


    @Override
    public void remove(int id) {
        String query ="DELETE  FROM suppliers WHERE id=?";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1,id);
        }
        catch (SQLException e){
            System.out.println(e.getErrorCode());
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        String query="SELECT * FROM suppliers";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                String name =resultSet.getString("name");
                String desc = resultSet.getString("description");
                Supplier supplier = new Supplier(name, desc);
                suppliers.add(supplier);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }

    public void clearSuppliersTable() {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute("TRUNCATE TABLE suppliers CASCADE; ALTER SEQUENCE suppliers_id_seq RESTART WITH 1;");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
