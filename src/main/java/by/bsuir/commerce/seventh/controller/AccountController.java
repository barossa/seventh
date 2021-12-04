package by.bsuir.commerce.seventh.controller;

import by.bsuir.commerce.seventh.entity.Cart;
import by.bsuir.commerce.seventh.entity.Product;
import by.bsuir.commerce.seventh.entity.User;
import by.bsuir.commerce.seventh.service.ProductService;
import by.bsuir.commerce.seventh.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    private ProductService productService;
    private UserService userService;

    public AccountController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping()
    public String accountPage() {
        return "account/account";
    }

    @PostMapping("/add-to-cart")
    public String addToUserCart(@RequestParam Long productId,
                                Principal principal,
                                HttpSession session){
        String username = principal.getName();
        Optional<User> userByUsername = userService.findByUsername(username);
        if(userByUsername.isPresent()){
            User user = userByUsername.get();
            Optional<Cart> cartOptional = productService.addToCart(user, productId);
            cartOptional.ifPresent(cart -> session.setAttribute("cart", cart));
        }
        return "blank";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromUserCart(@RequestParam Long productId, Principal principal){
        String username = principal.getName();
        Optional<User> userByUsername = userService.findByUsername(username);
        if(userByUsername.isPresent()){
            User user = userByUsername.get();
            productService.removeFromCart(user, productId);
        }
        return "redirect:/cart";
    }

    @GetMapping("/admin/add-product")
    public String addGoodPage() {
        return "account/addProduct";
    }

    @PostMapping("/admin/add-product")
    public String addProduct(@Valid Product product,
                             BindingResult bindingResult,
                             Map<String, Object> model,
                             MultipartFile[] files) {
        Map<String, List<String>> errorsMap = ControllerUtils.getErrorsMap(bindingResult);
        if (!bindingResult.hasErrors()) {
            Optional<Product> productOptional = productService.save(product, files);
            if(productOptional.isEmpty()){
                errorsMap.put("form", List.of());
            }
        }
        model.put("errors", errorsMap);

        return "account/addProduct";
    }
}
