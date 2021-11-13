package by.bsuir.commerce.seventh.controller;

import by.bsuir.commerce.seventh.entity.Product;
import by.bsuir.commerce.seventh.service.ProductService;
import by.bsuir.commerce.seventh.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
