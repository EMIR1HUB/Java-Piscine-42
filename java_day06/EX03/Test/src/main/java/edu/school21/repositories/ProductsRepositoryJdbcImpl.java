package edu.school21.repositories;

import edu.school21.models.Product;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private final Connection connection;

    public ProductsRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                products.add(new Product(
                        rs.getLong("identifier"),
                        rs.getString("name"),
                        rs.getInt("price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> products = Optional.empty();
        ResultSet rs = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE identifier = ?");
            statement.setLong(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                products = Optional.of(new Product(
                        rs.getLong("identifier"),
                        rs.getString("name"),
                        rs.getInt("price")
                ));
            } else {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET " +
                "name = ?, " +
                "price = ?" +
                "WHERE identifier = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE identifier = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
