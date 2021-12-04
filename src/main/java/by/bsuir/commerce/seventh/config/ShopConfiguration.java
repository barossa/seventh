package by.bsuir.commerce.seventh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ShopConfiguration {

    @Bean(name = "categories")
    public List<String> categories() {
        return List.of(
                "GAMING",
                "BUSINESS",
                "MEDIA",
                "ULTRABOOK",
                "NETBOOK",
                "BUDGETARY");
    }
}
