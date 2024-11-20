package com.example.demoenversaudited;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoEnversAuditedApplication implements CommandLineRunner {

    @Autowired
    EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(DemoEnversAuditedApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        entityManager.persist(product);
        entityManager.flush();

        // Обновляем продукт
        product.setPrice(150.0);
        entityManager.merge(product);
        entityManager.flush();
    }
}
