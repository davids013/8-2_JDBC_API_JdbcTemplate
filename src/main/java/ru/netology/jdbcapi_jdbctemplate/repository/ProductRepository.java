package ru.netology.jdbcapi_jdbctemplate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final String sqlScriptFile = "select_product_by_customer_id.sql";
    private String sqlRequest;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public String getProductName(String name) {
        if (sqlRequest == null)
            sqlRequest = read(sqlScriptFile);
        return jdbcTemplate.queryForObject(sqlRequest,
                (resultSet, i) -> resultSet.getString("product_name"), name);
//        Map<String, String> map = new HashMap<>();
//        map.put("name", name);
//        return namedParameterJdbcTemplate.queryForObject(sqlRequest, map,
//                (resultSet, i) -> resultSet.getString("product_name"));
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
