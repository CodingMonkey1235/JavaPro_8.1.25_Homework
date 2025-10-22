package org.example.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.allUsers.AllUsersDto;
import org.example.dto.allUsers.AllUsersSimpleDto;
import org.example.dto.allUsers.mapper.FindAllUsersMapper;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public AllUsersDto findAllUsers() {
        List<User> users = userRepository.findAll();
        return FindAllUsersMapper.mapFindAllUsers(users);
    }

    public AllUsersSimpleDto findAllUsersTest() {
        List<User> users = userRepository.findAll();
        List<String> usersDtos = users.stream().map(User::getUsername).toList();
        return new AllUsersSimpleDto(usersDtos);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
