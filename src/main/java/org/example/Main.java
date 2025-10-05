package org.example;

import org.example.config.ApplicationConfiguration;
import org.example.config.DatabaseConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        UserService userService = context.getBean(UserService.class);

        // create
        User user = null;
        try {
            user = userService.createUser("new user 01").orElseThrow(RuntimeException::new);
            System.out.println("Created " + user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // update
        user.setUsername("new user chaged name");
        try {
            userService.updateUser(user);
            System.out.println("Updated " + user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // delete
        try {
            userService.deleteUser(user);
            System.out.println("Deleted " + user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // find all
        try {
            List<User> users = userService.findAll();
            System.out.println("Found " + users.size() + " users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // find
        long foundUserId = 1L;
        try {
            User foundedUser = userService.findById(foundUserId).orElseThrow(RuntimeException::new);
            System.out.println("Found " + foundedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}