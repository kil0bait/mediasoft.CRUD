package com.rasul.crud.CRUD.service;

import com.rasul.crud.CRUD.dao.ProductDAO;
import com.rasul.crud.CRUD.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDAO productDAO;
    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @Override
    @Transactional
    public Product getProduct(String uuid) {
        return productDAO.getProduct(uuid);
    }

//    @Override
//    @Transactional
//    public void saveProduct(Product product) {
//        productDAO.saveProduct(product);
//    }
}
