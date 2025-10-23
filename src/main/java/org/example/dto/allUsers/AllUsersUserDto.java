package org.example.dto.allUsers;

import java.util.List;

public record AllUsersUserDto(String username, String email, List<AllUsersProductDto> products) {}
