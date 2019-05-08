package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public void remove(int id) {
        String query ="DELETE FROM suppliers WHERE id=?";

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
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
    }
}
