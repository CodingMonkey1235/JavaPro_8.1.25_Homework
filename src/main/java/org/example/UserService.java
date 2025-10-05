package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() throws SQLException {
        try {
            return userDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(Long id) throws SQLException {
        try {
            return userDao.find(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> createUser(String name) throws SQLException {
        try {
            return userDao.create(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) throws SQLException {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(User user) throws SQLException {
        try {
            userDao.delete(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
