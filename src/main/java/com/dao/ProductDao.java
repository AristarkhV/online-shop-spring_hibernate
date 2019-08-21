package com.dao;

import com.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    List<Product> getAll();

    void addProduct(Product value);

    void deleteProduct(Product value);

    void editProduct(Product value);

    Optional<Product> getProductById(Long id);
}
