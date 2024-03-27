package com.rasul.crud.CRUD.service;

import com.rasul.crud.CRUD.entity.Product;
import com.rasul.crud.CRUD.handler.exceptions.EmptyProductStorageException;
import com.rasul.crud.CRUD.handler.exceptions.NotFoundByArticleException;
import com.rasul.crud.CRUD.handler.exceptions.NotFoundByIdException;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    public List<Product> getAllProducts() throws EmptyProductStorageException;

    public void deleteAll() throws EmptyProductStorageException;

    public void saveProduct(Product product) throws SQLException;

    public Product getProductById(UUID uuid) throws NotFoundByIdException;

    public Product updateProductById(Product product, UUID uuid) throws SQLException, NotFoundByIdException;

    public void deleteProductById(UUID uuid) throws NotFoundByIdException;

    public Product getProductByArticle(String article) throws NotFoundByArticleException;

    public Product updateProductByArticle(Product product, String article) throws SQLException, NotFoundByArticleException;

    public void deleteProductByArticle(String article) throws NotFoundByArticleException;


}
