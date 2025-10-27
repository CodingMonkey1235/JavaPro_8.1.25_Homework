package org.example.dto.clientProducts.response;

import java.math.BigDecimal;
import java.util.List;

public record ClientProductsResponseDto(List<ClientProductsProductResponseDto> products) {
}
