package edu.school21.models;

import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private int price;
    
    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public int getPrice() {
        return price;
    }
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Objects.equals(name, product.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
