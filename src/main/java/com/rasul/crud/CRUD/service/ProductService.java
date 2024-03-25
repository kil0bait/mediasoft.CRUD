package com.rasul.crud.CRUD.service;

import com.rasul.crud.CRUD.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProduct(String uuid);

//    public void saveProduct(Product product);
}
