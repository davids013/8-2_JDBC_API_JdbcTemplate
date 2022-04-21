package ru.netology.jdbcapi_jdbctemplate.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.jdbcapi_jdbctemplate.db.entities.Customers;
import ru.netology.jdbcapi_jdbctemplate.db.entities.Orders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final String sqlScriptFile = "select_product_by_customer_id.sql";
    private String sqlRequest;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public String getProductName(String name) {
        if (sqlRequest == null) {
            sqlRequest = read(sqlScriptFile);
            addPairFor(name);
        }
        final String hqlRequest = sqlRequest
                .replace("?", "'" + name + "'")
                .replace(";", "")
                .replace("product_name", "productName")
                .replace("customer_id", "customerId")
                .replace("CUSTOMERS", "Customers")
                .replace("ORDERS", "Orders");
        final Query query = entityManager.createQuery(hqlRequest);
        return query.getSingleResult().toString();
    }

    private void addPairFor(String name) {
        final Customers c = new Customers(name, "Petrov", 20, "+7-999-...");
        final Orders o = new Orders(c, "Milk", 60);
        entityManager.persist(c);
        entityManager.persist(o);
    }

    private String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
