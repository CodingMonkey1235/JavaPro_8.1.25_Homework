package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // find all
        userRepository.findAll().forEach(it -> log.info(it.toString()));

        // find one
        User userById = userRepository.findById(3L).orElseThrow(EntityNotFoundException::new);
        log.info(String.valueOf(userById));

        User userByName = userRepository.findByUsername("first_user").orElseThrow(EntityNotFoundException::new);
        log.info(String.valueOf(userByName));

        User userByIdAndName = userRepository.findByIdAndUsername(2L, "first_user").orElseThrow(EntityNotFoundException::new);
        log.info(String.valueOf(userByIdAndName));

        // query
        long premiumUsersCount = userRepository.findPremiumGroupUsersCount();
        log.info("premiumUsersCount: " + premiumUsersCount);

        // projection
        UserProfileProjection userProfileProjection = userRepository
                .findByIdAndUsernameAndEmail(4L, "user4", "user4@mail.ru")
                .orElseThrow(EntityNotFoundException::new);
        log.info(userProfileProjection.toString());

        // update
        userById.setUsername("changed_username");
        User saveduser = userRepository.save(userById);
        System.out.println(saveduser.toString());

        // delete
        userRepository.delete(userById);
    }
}
