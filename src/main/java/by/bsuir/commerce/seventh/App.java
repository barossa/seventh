package by.bsuir.commerce.seventh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("by.bsuir.commerce.seventh.repo")
@EntityScan(basePackages = {"by.bsuir.commerce.seventh.entity"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
} 