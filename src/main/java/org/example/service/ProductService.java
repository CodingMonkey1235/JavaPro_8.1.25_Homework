package org.example.service;

import jakarta.transaction.Transactional;
import org.example.dto.allProducts.AllProductsDto;
import org.example.dto.allProducts.AllProductsProductDto;
import org.example.dto.allProducts.AllProductsProductUserDto;
import org.example.dto.allProducts.mapper.FindAllProductsMapper;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public AllProductsDto findAllProducts() {
        List<Product> products = productRepository.findAll();
        return FindAllProductsMapper.mapFindAllProducts(products);
    }

    public AllProductsProductDto findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        AllProductsProductUserDto userDto = new AllProductsProductUserDto(product.getUser().getUsername());
        AllProductsProductDto productDto =  new AllProductsProductDto(product.getAccountNumber(), product.getBalance(),
                product.getProductType(), userDto);
        return productDto;
    }

}
