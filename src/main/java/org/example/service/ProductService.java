package org.example.service;

import jakarta.transaction.Transactional;
import org.example.dto.allProducts.AllProductsDto;
import org.example.dto.allProducts.AllProductsProductDto;
import org.example.dto.allProducts.AllProductsProductUserDto;
import org.example.dto.allProducts.mapper.FindAllProductsMapper;
import org.example.dto.clientProducts.ClientProductsProductResponseDto;
import org.example.dto.clientProducts.ClientProductsResponseDto;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Transactional
    public void updateProductById(Long id, BigDecimal newBalance) throws NoSuchElementException {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        product.setBalance(newBalance);
        productRepository.save(product);
    }

    @Transactional
    public ClientProductsResponseDto findAllProductsByUserId(Long userId) {
        List<Product> products = productRepository.findAllProductsByUserId(userId);
        List<ClientProductsProductResponseDto> productsDto = products.stream().map(product -> {
            return new ClientProductsProductResponseDto(
                    product.getId(),
                    product.getAccountNumber(),
                    product.getBalance(),
                    product.getProductType()
            );
        }).toList();
        return new ClientProductsResponseDto(productsDto);
    }
}
