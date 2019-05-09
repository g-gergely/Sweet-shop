package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.Currency;
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
                int supplierId = resultSet.getInt("supplier_id");
                Supplier supplier = null;
                int categoryId = resultSet.getInt("category_id");
                ProductCategory productCategory = null;
                //todo: Complete the argument list with Supplier and Category
                product = new Product(id, name, defaulPrice, currency, description);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return product;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM products WHERE id = ?";

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
    public List<Product> getAll() {
        ProductCategoryDao productDao = new ProductCategoryDaoJdbc();
        SupplierDao supplierDao= new SupplierDaoJdbc();
        List<Product> products = new ArrayList<>();

        String query="SELECT * FROM products";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name =resultSet.getString("name");
                String desc = resultSet.getString("description");
                float defaultPrice = resultSet.getFloat("default_price");
                String currency =resultSet.getString("currency");
                int supplierId = resultSet.getInt("supplier_id");
                int productCategoryId = resultSet.getInt("category_id");
                Product product= new Product(id,name,defaultPrice,currency,desc,productDao.find(productCategoryId)
                        ,supplierDao.find(supplierId));
                products.add(product);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();
        ProductCategoryDao productDao = new ProductCategoryDaoJdbc();
        SupplierDao supplierDao = new SupplierDaoJdbc();

        String query = "SELECT * FROM products WHERE supplier_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, supplier.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String desc = resultSet.getString("description");
                float defaultPrice = resultSet.getFloat("default_price");
                String currency = resultSet.getString("currency");
                int supplierId = resultSet.getInt("supplier_id");
                int productCategoryId = resultSet.getInt("category_id");
                Product product = new Product(id, name, defaultPrice, currency, desc, productDao.find(productCategoryId)
                        , supplierDao.find(supplierId));
                products.add(product);

            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return products;
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
}

