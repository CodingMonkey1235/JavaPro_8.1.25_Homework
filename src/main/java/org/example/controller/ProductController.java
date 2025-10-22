package org.example.controller;

import org.example.dto.allProducts.AllProductsDto;
import org.example.dto.allProducts.AllProductsProductDto;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/")
    public AllProductsDto findAllProducts() {
        return productService.findAllProducts();
    }

    @RequestMapping(value = "/{id}")
    public Optional<AllProductsProductDto> findProductById(@PathVariable("id") long id) {
        return productService.findProductById(id);
    }

}
