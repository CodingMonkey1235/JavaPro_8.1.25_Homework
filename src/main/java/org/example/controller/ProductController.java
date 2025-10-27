package org.example.controller;

import org.example.dto.allProducts.AllProductsDto;
import org.example.dto.allProducts.AllProductsProductDto;
import org.example.dto.clientProducts.ClientProductsResponseDto;
import org.example.dto.updateProduct.ClientProductUpdateRequestDto;
import org.example.dto.updateProduct.ClientProductUpdateResponseDto;
import org.example.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

import java.util.NoSuchElementException;

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

    @PostMapping(value = "/")
    public ClientProductUpdateResponseDto updateProductById(@RequestBody ClientProductUpdateRequestDto requestDto) {
        try {
            productService.updateProductById(requestDto.productId(), requestDto.newBalance());
            return new ClientProductUpdateResponseDto("SUCCESS", "Product updated successfully");
        } catch (NoSuchElementException e) {
            return new ClientProductUpdateResponseDto(e.getClass().getSimpleName(), "Product not found");
        }
    }

    @GetMapping(value = "/user/{id}/")
    ClientProductsResponseDto findAllProductsByUserId(@PathVariable("id") long userId) {
        return productService.findAllProductsByUserId(userId);
    }
}
