package org.example.dto.allUsers.mapper;

import org.example.dto.allUsers.AllUsersDto;
import org.example.dto.allUsers.AllUsersProductDto;
import org.example.dto.allUsers.AllUsersUserDto;
import org.example.entity.User;
import org.example.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllUsersMapper {
    public static AllUsersDto mapFindAllUsers(List<User> users) {
        List<AllUsersUserDto> usersDto = users.stream().map(user -> {
            List<AllUsersProductDto> usersProductDtos = user.getProducts().stream()
                    .map( item -> {
                        return new AllUsersProductDto(
                                item.getAccountNumber(),
                                item.getBalance(),
                                item.getProductType()
                        );
                    }).collect(Collectors.toList());
            return new AllUsersUserDto(user.getUsername(), user.getEmail(), usersProductDtos);
        }).collect(Collectors.toList());
        return new AllUsersDto(usersDto);
    }
}
