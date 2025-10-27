package org.example.dto.clientProduct.response;

import java.math.BigDecimal;

public record ClientProductResponseDto(String accountNumber, BigDecimal balance, String productType) {
}
