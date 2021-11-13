package by.bsuir.commerce.seventh.service.impl;

import by.bsuir.commerce.seventh.entity.User;
import by.bsuir.commerce.seventh.repo.UserRepo;
import by.bsuir.commerce.seventh.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }
}
