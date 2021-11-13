package by.bsuir.commerce.seventh.repo;

import by.bsuir.commerce.seventh.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findById(long id);
}
