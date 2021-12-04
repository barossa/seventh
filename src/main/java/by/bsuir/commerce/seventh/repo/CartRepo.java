package by.bsuir.commerce.seventh.repo;

import by.bsuir.commerce.seventh.entity.Cart;
import by.bsuir.commerce.seventh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByOwner(User owner);
}
