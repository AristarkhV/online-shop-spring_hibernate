package com.service.impl;

import com.dao.ProductDao;
import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    @Transactional(readOnly = false)
    public void addProduct(Product value) {
        productDao.addProduct(value);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProduct(Product value) {
        productDao.deleteProduct(value);
    }

    @Override
    @Transactional(readOnly = false)
    public void editProduct(Product value) {
        productDao.editProduct(value);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
