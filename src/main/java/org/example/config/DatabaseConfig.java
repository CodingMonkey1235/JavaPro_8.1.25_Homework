package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import org.example.UserDao;
import org.example.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost/Javapro_Homework?user=ash&password=");
        dataSource.setMaximumPoolSize(4);
        return dataSource;
    }

    @Bean
    public UserDao userDao(HikariDataSource dataSource) {
        return new UserDao(dataSource);
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }
}