package org.example.dto.paymentExecute;

import java.math.BigDecimal;

public record PaymentExecuteRequestDto(long productId, BigDecimal writeoffAmount) {
}
