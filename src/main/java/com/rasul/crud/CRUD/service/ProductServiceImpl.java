package com.rasul.crud.CRUD.service;

import com.rasul.crud.CRUD.dao.ProductRepository;
import com.rasul.crud.CRUD.entity.Product;
import com.rasul.crud.CRUD.handler.exceptions.EmptyProductStorageException;
import com.rasul.crud.CRUD.handler.exceptions.NotFoundByArticleException;
import com.rasul.crud.CRUD.handler.exceptions.NotFoundByIdException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() throws EmptyProductStorageException {
        List<Product> allProducts = productRepository.findAll();
        if (allProducts.isEmpty()){
            throw new EmptyProductStorageException();
        }
        return allProducts;
    }

    @Override
    public void deleteAll() throws EmptyProductStorageException {
        if (productRepository.count() == 0) {
            throw new EmptyProductStorageException();
        }
        productRepository.deleteAll();
    }

    @Override
    public void saveProduct(Product product) throws SQLException {
        try{
            productRepository.save(product);
        }catch(Exception ex){
            throw new SQLException(ex.getMessage());
        }
    }

    @Override
    public Product getProductById(UUID uuid) throws NotFoundByIdException {
        Optional<Product> optional = productRepository.findById(uuid);
        if(optional.isEmpty()){
            throw new NotFoundByIdException();
        }
        return optional.get();
    }

    @Override
    public Product updateProductById(Product updatedProduct, UUID uuid) throws SQLException, NotFoundByIdException {
        if (!productRepository.existsById(uuid)) {
            throw new NotFoundByIdException();
        }
        Product existingProduct = productRepository.getProductById(uuid);

        if (Objects.nonNull(updatedProduct.getName())
                && !"".equalsIgnoreCase(
                updatedProduct.getName())) {
            existingProduct.setName(
                    updatedProduct.getName());
        }
        if (Objects.nonNull(updatedProduct.getArticle()) && !updatedProduct.getArticle().isEmpty()) {
            existingProduct.setArticle(updatedProduct.getArticle());
        }
        if (Objects.nonNull(updatedProduct.getDescription())
                && !"".equalsIgnoreCase(
                updatedProduct.getDescription())) {
            existingProduct.setDescription(
                    updatedProduct.getDescription());
        }
        if (Objects.nonNull(updatedProduct.getPrice())) {
            existingProduct.setPrice(
                    updatedProduct.getPrice());
        }
        if (Objects.nonNull(updatedProduct.getCount())) {
            existingProduct.setCount(
                    updatedProduct.getCount());
        }
        if (Objects.nonNull(updatedProduct.getCategory())
                && !"".equalsIgnoreCase(
                updatedProduct.getCategory())) {
            existingProduct.setCategory(
                    updatedProduct.getCategory());
        }

        try {
            productRepository.save(existingProduct);
            return existingProduct;
        }
        catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void deleteProductById(UUID uuid) throws NotFoundByIdException {
        if (!productRepository.existsById(uuid)) {
            throw new NotFoundByIdException();
        }
        productRepository.deleteById(uuid);
    }

    @Override
    public Product getProductByArticle(String article) throws NotFoundByArticleException {
        if(!productRepository.existsByArticle(article)){
            throw new NotFoundByArticleException();
        }
        return productRepository.getProductByArticle(article);
    }

    public Product updateProductByArticle(Product updatedProduct, String article) throws SQLException, NotFoundByArticleException {
        if (!productRepository.existsByArticle(article)) {
            throw new NotFoundByArticleException();
        }
        Product existingProduct = productRepository.getProductByArticle(article);


        if (Objects.nonNull(updatedProduct.getName())
                && !"".equalsIgnoreCase(
                updatedProduct.getName())) {
            existingProduct.setName(
                    updatedProduct.getName());
        }
        if (Objects.nonNull(updatedProduct.getDescription())
                && !"".equalsIgnoreCase(
                updatedProduct.getDescription())) {
            existingProduct.setDescription(
                    updatedProduct.getDescription());
        }
        if (Objects.nonNull(updatedProduct.getPrice())) {
            existingProduct.setPrice(
                    updatedProduct.getPrice());
        }
        if (Objects.nonNull(updatedProduct.getCount())) {
            existingProduct.setCount(
                    updatedProduct.getCount());
        }
        if (Objects.nonNull(updatedProduct.getCategory())
                && !"".equalsIgnoreCase(
                updatedProduct.getCategory())) {
            existingProduct.setCategory(
                    updatedProduct.getCategory());
        }

        try {
            productRepository.save(existingProduct);
            return existingProduct;
        }
        catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void deleteProductByArticle(String article) throws NotFoundByArticleException {
        Product product = productRepository.findByArticle(article);
        if (product == null) {
            throw new NotFoundByArticleException();
        }
        productRepository.delete(product);
    }

}
