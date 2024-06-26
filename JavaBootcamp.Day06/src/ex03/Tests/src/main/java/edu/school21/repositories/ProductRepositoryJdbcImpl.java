package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryJdbcImpl implements ProductRepository{
    private DataSource ds;
    
    public ProductRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }
    
    @Override
    public List<Product> findAll() {
        List<Product> products = new LinkedList<>();
        try (Connection connection = ds.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM test;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> optionalProduct = Optional.empty();
        try (Connection connection = ds.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM test WHERE id = ?;");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                optionalProduct = Optional.of(new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionalProduct;
    }
    
    @Override
    public void update(Product product) {
        try (Connection connection = ds.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE test " +
                        "SET name = ?, price = ? " +
                        "WHERE id = ?;");
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setLong(3, product.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void save(Product product) {
        try (Connection connection = ds.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO test VALUES (?, ?, ?);");
            ps.setLong(1, product.getId());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(Long id) {
        try (Connection connection = ds.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM test WHERE id = ?;");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
