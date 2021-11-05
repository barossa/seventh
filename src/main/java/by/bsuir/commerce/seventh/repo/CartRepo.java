package by.bsuir.commerce.seventh.repo;

import by.bsuir.commerce.seventh.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
}
