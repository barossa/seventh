package by.bsuir.commerce.seventh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import javax.annotation.PostConstruct;

@Controller()
public class SeventhController {

    /*@PostConstruct*/
    @GetMapping("/")
    public String index() {
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
    @GetMapping("/product")
    public String toProduct() {
        return "product";
    }

    @GetMapping("/store")
    public String toStore() {
        return "store";
    }

    @PostConstruct
    @GetMapping("/cart")
    public String toCart() {
        return "cart";
    }

    @GetMapping("/catalog")
    public String catalog(@RequestAttribute(required = false) Integer category) {
        return "catalog";
    }

}
