package org.example;

import org.example.config.DatabaseConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DatabaseConfig.class);
        UserService userService = context.getBean(UserService.class);

        // create
        Optional<User> user = userService.createUser("new user 01");
        if (user.isPresent()) {
            System.out.println("Created " + user.get());
        } else {
            System.out.println("Error creating user");
        }

        // update
        User changedUser = user.get();
        changedUser.setUsername("new user chaged name");
        boolean isUpdated = userService.updateUser(changedUser);
        if (isUpdated) {
            System.out.println("Updated " + user.get());
        } else {
            System.out.println("Error updating " + changedUser);
        }

        // delete
        boolean isDeleted = userService.deleteUser(user.get());
        if (isDeleted) {
            System.out.println("Deleted " + user.get());
        } else {
            System.out.println("Error deleting user");
        }

        // find all
        List<User> users = userService.findAll();
        System.out.println("Found " + users.size() + " users");

        // find
        long foundUserId = 1L;
        Optional<User> foundedUser = userService.findById(foundUserId);
        if (foundedUser.isPresent()) {
            System.out.println("Found " + foundedUser.get());
        } else {
            System.out.println("Error finding user with id " + foundUserId);
        }
    }
}