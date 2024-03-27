package org.example.warehousemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.warehousemanager.dao.ProductRepository;
import org.example.warehousemanager.model.Product;
import org.example.warehousemanager.model.exception.EmptyProductStorageException;
import org.example.warehousemanager.model.exception.NotFoundByArticleException;
import org.example.warehousemanager.model.exception.NotFoundByIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() throws EmptyProductStorageException {
        List<Product> allProducts = productRepository.findAll();
//        if (allProducts.isEmpty()) {
//            throw new EmptyProductStorageException();
//        }
        return allProducts;
    }
//
//    @Override
//    public void deleteAll() throws EmptyProductStorageException {
//        if (productRepository.count() == 0) {
//            throw new EmptyProductStorageException();
//        }
//        productRepository.deleteAll();
//    }

    public void saveProduct(Product product) throws SQLException {
        try {
            productRepository.save(product);
        } catch (Exception ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public Product getProductById(UUID uuid) throws NotFoundByIdException {
        Optional<Product> optional = productRepository.findById(uuid);
//        if (optional.isEmpty()) {
//            throw new NotFoundByIdException();
//        }
        return optional.orElseThrow(NotFoundByIdException::new);
    }

    public Product updateProductById(Product updatedProduct, UUID uuid) throws SQLException, NotFoundByIdException {
//        if (!productRepository.existsById(uuid)) {
//            throw new NotFoundByIdException();
//        }
        Product existingProduct = productRepository.findById(uuid)
                .orElseThrow(NotFoundByIdException::new);

        if (Objects.nonNull(updatedProduct.getName()) && updatedProduct.getName().isEmpty()) {
            existingProduct.setName(updatedProduct.getName());
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
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

//    @Override
    public void deleteProductById(UUID uuid) throws NotFoundByIdException {
//        if (!productRepository.existsById(uuid)) {
//            throw new NotFoundByIdException();
//        }
        Product product = productRepository.findById(uuid).orElseThrow(NotFoundByIdException::new);
        productRepository.delete(product);
    }

//    @Override
    public Product getProductByArticle(String article) throws NotFoundByArticleException {
        if (!productRepository.existsByArticle(article)) {
            throw new NotFoundByArticleException();
        }
        return productRepository.getProductByArticle(article).i;
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
        } catch (Exception e) {
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
