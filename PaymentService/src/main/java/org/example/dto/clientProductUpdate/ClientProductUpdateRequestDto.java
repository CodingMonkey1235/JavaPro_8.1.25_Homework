package org.example.dto.clientProductUpdate;

import java.math.BigDecimal;

public record ClientProductUpdateRequestDto(long productId, BigDecimal newBalance) {
}
