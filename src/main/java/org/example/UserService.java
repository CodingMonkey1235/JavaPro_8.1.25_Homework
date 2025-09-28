package org.example;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(Long id) {
        return userDao.find(id);
    }

    public Optional<User> createUser(String name) {
        return userDao.create(name);
    }

    public boolean updateUser(User user) {
        return userDao.update(user);
    }

    public boolean deleteUser(User user) {
        return userDao.delete(user);
    }
}
