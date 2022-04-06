package ru.netology.jdbcapi_jdbctemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.jdbcapi_jdbctemplate.service.ProductService;

@RestController
public class ProductController {
    private static ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products/fetch-product")
    public String getProductName(@RequestParam String name) {
        return service.getProductName(name);
    }
}
