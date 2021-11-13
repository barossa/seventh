package by.bsuir.commerce.seventh.service;

import by.bsuir.commerce.seventh.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> save(Product good, MultipartFile[] files);

    Product findById(long id);

    List<Product> findTopSales();

    List<Product> findNewest();
}
