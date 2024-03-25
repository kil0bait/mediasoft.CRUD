package com.rasul.crud.CRUD.dao;

import com.rasul.crud.CRUD.entity.Product;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private EntityManager entityManager; // Зависимости все подтянуты, конфиг прописан в application.properties

    @Override
    public List<Product> getAllProducts() {
        Session session = entityManager.unwrap(Session.class);
        List<Product> allProducts = session.createQuery("from Product", Product.class).getResultList();

        return allProducts;
    }

    @Override
    public Product getProduct(String uuid) {
        Session session = entityManager.unwrap((Session.class));
        Product product = session.get(Product.class, uuid);
        return product;
    }

//    @Override
//    public void saveProduct(Product product) {
//        Session session = entityManager.
//    }


}
