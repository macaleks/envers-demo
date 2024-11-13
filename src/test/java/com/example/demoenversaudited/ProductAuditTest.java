package com.example.demoenversaudited;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ProductAuditTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testProductAuditing() {
        // Создаем и сохраняем продукт
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        // Обновляем продукт
        product.setPrice(150.0);
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();

        // Получаем аудит-данные
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        Product firstRevision = auditReader.find(Product.class, product.getId(), 1);
        Product secondRevision = auditReader.find(Product.class, product.getId(), 2);

        // Проверяем значения
        assertThat(firstRevision.getPrice()).isEqualTo(100.0);
        assertThat(secondRevision.getPrice()).isEqualTo(150.0);
    }
}

