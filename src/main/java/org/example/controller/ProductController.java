package org.example.controller;

import org.example.dto.allProducts.AllProductsDto;
import org.example.dto.allProducts.AllProductsProductDto;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/")
    public AllProductsDto findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping(value = "/{id}")
    public AllProductsProductDto findProductById(@PathVariable("id") long id) {
        return productService.findProductById(id);
    }

}
