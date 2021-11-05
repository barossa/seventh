package by.bsuir.commerce.seventh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class SeventhController {

@GetMapping("/")
    public String index(){
    return "index";
}

@GetMapping("/blank")
    public String toBlank(){
    return "blank";
}

@GetMapping("/checkout")
    public String toCheckout(){
        return "checkout";
    }

    @GetMapping("/product")
    public String toProduct(){
        return "product";
    }

    @GetMapping("/store")
    public String toStore(){
        return "store";
    }

    @GetMapping("/cart")
    public String toCart(){
    return "cart";
    }

}
