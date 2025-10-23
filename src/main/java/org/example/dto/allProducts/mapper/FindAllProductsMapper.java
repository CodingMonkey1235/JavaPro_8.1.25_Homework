package org.example.dto.allProducts.mapper;

import org.example.dto.allProducts.AllProductsDto;
import org.example.dto.allProducts.AllProductsProductDto;
import org.example.dto.allProducts.AllProductsProductUserDto;
import org.example.entity.Product;
import org.example.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllProductsMapper {
    public static AllProductsDto mapFindAllProducts(List<Product> products) {
        List<AllProductsProductDto> productsDto = products.stream()
                .map(item -> {
                    User user = item.getUser();
                    return new AllProductsProductDto(
                            item.getAccountNumber(),
                            item.getBalance(),
                            item.getProductType(),
                            new AllProductsProductUserDto(user.getUsername())
                    );
                }).toList();
        return new AllProductsDto(productsDto);
    }
}
