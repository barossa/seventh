package by.bsuir.commerce.seventh.repo;

import by.bsuir.commerce.seventh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
