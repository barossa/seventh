package by.bsuir.commerce.seventh.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeventhController {

@GetMapping("/")
    public String testMapping(Model model){
    model.addAttribute("title", "Test model title");
    model.addAttribute("message", "Test model message!\n DYNAMIC HELLO MOTHER FUCKERS!");
    return "test";
}

}
