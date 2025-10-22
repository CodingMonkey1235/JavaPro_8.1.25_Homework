package org.example.dto.allProducts;

import java.math.BigDecimal;

public record AllProductsProductDto(
        String accountNumber,
        BigDecimal balance,
        String productType,
        AllProductsProductUserDto user
) {}
