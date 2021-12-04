package by.bsuir.commerce.seventh.service.impl;

import by.bsuir.commerce.seventh.entity.*;
import by.bsuir.commerce.seventh.repo.CartRepo;
import by.bsuir.commerce.seventh.repo.ProductRepo;
import by.bsuir.commerce.seventh.repo.UserRepo;
import by.bsuir.commerce.seventh.service.ProductService;
import by.bsuir.commerce.seventh.service.util.ProductUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {
    public static final String CATEGORY = "category";
    public static final String QUERY = "query";
    public static final String PRICE_MIN = "price_min";
    public static final String PRICE_MAX = "price_max";
    public static final String BRAND = "brand";

    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CartRepo cartRepo;
    private final List<String> categories;

    public ProductServiceImpl(ProductRepo goodRepo,
                              UserRepo userRepo,
                              CartRepo cartRepo,
                              List<String> categories) {
        this.productRepo = goodRepo;
        this.userRepo = userRepo;
        this.cartRepo = cartRepo;
        this.categories = categories;
    }

    @Override
    public Optional<Product> save(Product good, MultipartFile[] files) {
        List<Image> images = good.getImages();
        for (MultipartFile file : files) {
            try {
                String base64Image = Base64.getEncoder().encodeToString(file.getBytes());
                Image image = new Image(base64Image);
                images.add(image);
            } catch (IOException e) {
                return Optional.empty();
            }
        }
        Product savedProduct = productRepo.save(good);
        return Optional.of(savedProduct);
    }

    @Override
    public Optional<Product> findById(long id) {
        Product product = productRepo.findById(id);
        List<Review> reviews = product.getReviews();
        return Optional.of(product);
    }

    @Override
    public List<Product> findTopSales(int quantity) {
        List<Product> topSales = productRepo.findAll(Sort.by("sales"));
        List<Product> result;
        if (topSales.size() <= quantity) {
            result = topSales;
        } else {
            result = topSales.subList(0, quantity);
        }
        initializeLazyFields(result);
        return result;
    }

    @Override
    public List<Product> findNewest(int quantity) {
        List<Product> newest = productRepo.findAll();
        List<Product> result;
        if (newest.size() <= quantity) {
            result = newest;
        } else {
            result = newest.subList(0, quantity);
        }
        initializeLazyFields(result);
        return result;
    }

    @Override
    public Optional<Cart> addToCart(User user, long productId) {
        Product product = productRepo.findById(productId);
        if (product == null) {
            return Optional.empty();
        }
        Optional<Cart> cartByUser = findCartByUser(user);
        Cart byOwner = cartByUser.get();
        List<Product> products = byOwner.getProducts();
        products.add(product);
        Cart savedCart = cartRepo.save(byOwner);
        return Optional.of(savedCart);
    }

    @Override
    public Optional<Cart> removeFromCart(User user, long productId) {
        Optional<Cart> cartByUser = findCartByUser(user);
        Cart cart = cartByUser.get();
        List<Product> products = cart.getProducts();
        ProductUtils.removeFirstOccurrence(products, productId);
        cartRepo.save(cart);
        return Optional.of(cart);
    }

    @Override
    public Optional<Cart> findCartByUser(User user) {
        Cart cartByOwner = cartRepo.findByOwner(user);
        if (cartByOwner == null) {
            cartByOwner = new Cart();
            cartByOwner.setOwner(user);
        }
        Cart saved = cartRepo.save(cartByOwner);
        return Optional.of(saved);
    }

    @Override
    public boolean delete(long productId) {
        Product product = productRepo.findById(productId);
        if(product == null){
            return false;
        }else{
            productRepo.delete(product);
            return true;
        }
    }

    @Override
    public List<Product> findProducts(String query, List<Integer> categories, int minPrice, int maxPrice) {
        List<Product> allProducts = productRepo.findAll();
        Predicate<Product> queryPredicate = product -> product.getName().toLowerCase().contains(query.toLowerCase(Locale.ROOT));
        Predicate<Product> minPricePredicate = product -> product.getPrice().intValue() >= minPrice;
        Predicate<Product> maxPricePredicate = product -> product.getPrice().intValue() <= minPrice;

        List<String> categoryNames = categories.stream()
                .filter(index -> index < this.categories.size() && index >= 0)
                .map(this.categories::get)
                .collect(Collectors.toList());
        Predicate<Product> categoryPredicate = product -> categoryNames.contains(product.getCategory());


        List<Product> products = allProducts.stream()
                .filter(!categories.isEmpty() ? categoryPredicate : product -> true)
                .filter(query != null && !query.isEmpty() ? queryPredicate : product -> true)
                .filter(minPricePredicate)
                .filter(maxPrice > 0 ? maxPricePredicate : product -> true)
                .collect(Collectors.toList());
        initializeLazyFields(products);
        return products;
    }

    private void initializeLazyFields(List<Product> products) {
        for (Product product : products) {
            product.getReviews().size();
        }
    }
}
