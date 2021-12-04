package by.bsuir.commerce.seventh.controller;

import by.bsuir.commerce.seventh.entity.Cart;
import by.bsuir.commerce.seventh.entity.Product;
import by.bsuir.commerce.seventh.entity.User;
import by.bsuir.commerce.seventh.repo.ProductRepo;
import by.bsuir.commerce.seventh.service.ProductService;
import by.bsuir.commerce.seventh.service.UserService;
import by.bsuir.commerce.seventh.service.util.ProductUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller()
public class SeventhController {
    private static final String TOP_SALES_KEY = "topSales";
    private static final String NEWEST_KEY = "newest";
    private static final String CART = "cart";
    private static final String CATALOG = "catalog";
    private static final String NEXT = "next";
    private static final String PREVIOUS = "previous";
    private static final String CURRENT = "current";
    private static final String PAGES = "pages";


    //Products on main page by each section
    private static final int PRODUCT_QUANTITY = 10;

    @Autowired
    private ProductRepo productRepo;

    private final ProductService productService;
    private final UserService userService;

    public SeventhController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    /*@PostConstruct*/
    @GetMapping("/")
    public String index(Model model) {
        List<Product> newest = productService.findNewest(PRODUCT_QUANTITY);
        List<Product> topSales = productService.findTopSales(PRODUCT_QUANTITY);
        model.addAttribute(NEWEST_KEY, newest);
        model.addAttribute(TOP_SALES_KEY, topSales);

        return "index";
    }

    @GetMapping("/blank")
    public String toBlank() {
        return "blank";
    }

    @GetMapping("/checkout")
    public String toCheckout() {
        return "checkout";
    }

    /*@PostConstruct*/
    @GetMapping("/product/{id}")
    public String toProduct(@PathVariable() long id,
                            Model model) {
        if (id > 0) {
            Optional<Product> product = productService.findById(id);
            if (product.isPresent()) {
                model.addAttribute("product", product.get());
                List<Product> newest = productService.findNewest(4);
                model.addAttribute("related", newest);
                return "product";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/store")
    public String toStore() {
        return "store";
    }


    @GetMapping("/cart")
    public String toCart(Authentication authentication, HttpSession session, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {//User
            String username = authentication.getName();
            Optional<User> userByUsername = userService.findByUsername(username);
            if (userByUsername.isPresent()) {
                User user = userByUsername.get();
                Optional<Cart> cartByUser = productService.findCartByUser(user);
                cartByUser.ifPresent(cart -> session.setAttribute("cart", cart));
            }

        } else {//Guest
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        }

        return "cart";
    }

    @GetMapping("/catalog")
    public String catalog(@RequestParam(required = false) List<Integer> category,
                          @RequestParam(required = false, defaultValue = "0") int priceMin,
                          @RequestParam(required = false, defaultValue = "0") int priceMax,
                          @RequestParam(required = false) String query,
                          @RequestParam(required = false, defaultValue = "0") int page,
                          Model model) {
        if (category == null) {
            category = Collections.emptyList();
        }
        List<Product> products = productService.findProducts(query, category, priceMin, priceMax);
        model.addAttribute(CATALOG, products);
        model.addAttribute(CURRENT, 0);
        model.addAttribute(PREVIOUS, -1);
        model.addAttribute(NEXT, -1);
        model.addAttribute(PAGES, List.of(1, 2, 3, 4));

        return "catalog";
    }

    @PostMapping("/add-to-cart")
    public String addToSessionScopeCart(HttpSession session, @RequestParam Long productId) {
        Cart cart = (Cart) session.getAttribute(CART);
        if (cart == null) {
            cart = new Cart();
        }
        List<Product> products = cart.getProducts();
        Optional<Product> byId = productService.findById(productId);
        byId.ifPresent(products::add);
        session.setAttribute("cart", cart);
        return "blank";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromSessionCart(HttpSession session, @RequestParam Long productId) {
        Cart cart = (Cart) session.getAttribute(CART);
        if (cart != null) {
            List<Product> products = cart.getProducts();
            List<Product> reduced = ProductUtils.removeFirstOccurrence(products, productId);
            cart.setProducts(reduced);
            session.setAttribute("cart", cart);
        }
        return "cart";
    }
}
