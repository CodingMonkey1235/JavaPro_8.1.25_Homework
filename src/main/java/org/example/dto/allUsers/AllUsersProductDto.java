package org.example.dto.allUsers;

import java.math.BigDecimal;

public record AllUsersProductDto(String accountNumber, BigDecimal balance, String productType) {
}
