package org.example.service;

import jakarta.transaction.Transactional;
import org.example.dto.allProducts.AllProductsDto;
import org.example.dto.allProducts.AllProductsProductDto;
import org.example.dto.allProducts.AllProductsProductUserDto;
import org.example.dto.allProducts.mapper.FindAllProductsMapper;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public AllProductsDto findAllProducts() {
        List<Product> products = productRepository.findAll();
        return FindAllProductsMapper.mapFindAllProducts(products);
    }

    public Optional<AllProductsProductDto> findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElse(null);
        AllProductsProductUserDto userDto = new AllProductsProductUserDto(product.getUser().getUsername());
        AllProductsProductDto productDto =  new AllProductsProductDto(product.getAccountNumber(), product.getBalance(),
                product.getProductType(), userDto);
        return Optional.of(productDto);
    }

    public void run(String... args) throws Exception {

    }

}
