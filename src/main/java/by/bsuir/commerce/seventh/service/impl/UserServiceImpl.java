package by.bsuir.commerce.seventh.service.impl;

import by.bsuir.commerce.seventh.entity.User;
import by.bsuir.commerce.seventh.repo.UserRepo;
import by.bsuir.commerce.seventh.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public Optional<User> save(User user) {
        User savedUser = userRepo.save(user);
        return Optional.of(savedUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User userByLogin = userRepo.findByLogin(username);
        return Optional.of(userByLogin);
    }


}
