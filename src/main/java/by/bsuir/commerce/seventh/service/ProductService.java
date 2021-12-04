package by.bsuir.commerce.seventh.service;

import by.bsuir.commerce.seventh.entity.Cart;
import by.bsuir.commerce.seventh.entity.Product;
import by.bsuir.commerce.seventh.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Optional<Product> save(Product good, MultipartFile[] files);

    Optional<Product> findById(long id);

    List<Product> findTopSales(int quantity);

    List<Product> findNewest(int quantity);

    Optional<Cart> addToCart(User user, long productId);

    Optional<Cart> removeFromCart(User user, long productId);

    Optional<Cart> findCartByUser(User user);

    boolean delete(long productId);

    List<Product> findProducts(String query, List<Integer> categories, int priceMin, int priceMax);


}
