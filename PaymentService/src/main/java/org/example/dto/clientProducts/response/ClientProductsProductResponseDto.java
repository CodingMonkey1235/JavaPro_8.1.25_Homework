package org.example.dto.clientProducts.response;

import java.math.BigDecimal;

public record ClientProductsProductResponseDto(
        long productId,
        String accountNumber,
        BigDecimal balance,
        String productType
) {}
