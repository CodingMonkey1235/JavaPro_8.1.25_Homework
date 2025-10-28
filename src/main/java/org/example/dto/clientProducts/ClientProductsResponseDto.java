package org.example.dto.clientProducts;

import org.example.dto.allProducts.AllProductsProductUserDto;

import java.math.BigDecimal;
import java.util.List;

public record ClientProductsResponseDto(List<ClientProductsProductResponseDto> products) {
}
