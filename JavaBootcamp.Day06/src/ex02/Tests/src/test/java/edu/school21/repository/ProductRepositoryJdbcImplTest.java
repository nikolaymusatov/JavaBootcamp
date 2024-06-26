package edu.school21.repository;

import edu.school21.models.Product;
import edu.school21.repositories.ProductRepository;
import edu.school21.repositories.ProductRepositoryJdbcImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryJdbcImplTest {
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = createLinkedList(
            new Product[] {
            new Product(1, "cup", 100),
            new Product(2, "spoon", 30),
            new Product(3, "fork", 35),
            new Product(4, "knife", 40),
            new Product(5, "plate", 80)});
    
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "spoon", 30);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(5L, "plate", 100);
    final Product EXPECTED_SAVED_PRODUCT = new Product(6L, "teapot", 300);
    
    private EmbeddedDatabase db;
    
    @BeforeEach
    public void init() {
        this.db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }
    
    @Test
    public void checkFindAll() {
        ProductRepository pr = new ProductRepositoryJdbcImpl(this.db);
        assertEquals(pr.findAll(), EXPECTED_FIND_ALL_PRODUCTS);
    }
    
    @Test
    public void checkFindById() {
        ProductRepository pr = new ProductRepositoryJdbcImpl(this.db);
        assertEquals(pr.findById(2L).orElse(null), EXPECTED_FIND_BY_ID_PRODUCT);
    }
    
    @Test
    public void checkUpdate() {
        ProductRepository pr = new ProductRepositoryJdbcImpl(this.db);
        pr.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(pr.findById(5L).orElse(null), EXPECTED_UPDATED_PRODUCT);
    }
    
    @Test
    public void checkSave() {
        ProductRepository pr = new ProductRepositoryJdbcImpl(this.db);
        pr.save(EXPECTED_SAVED_PRODUCT);
        assertEquals(pr.findById(6L).orElse(null), EXPECTED_SAVED_PRODUCT);
    }

    @Test
    public void checkDelete() {
        ProductRepository pr = new ProductRepositoryJdbcImpl(this.db);
        pr.delete(3L);
        assertNull(pr.findById(3L).orElse(null));
    }
    
    @AfterEach
    public void deinit() {
        this.db.shutdown();
    }
    
    private List<Product> createLinkedList(Product[] products) {
        List<Product> list = new LinkedList<>();
        Collections.addAll(list, products);
        return list;
    }
}
