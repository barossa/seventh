package by.bsuir.commerce.seventh.service;

import by.bsuir.commerce.seventh.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);
    Optional<User> findByUsername(String username);
}
