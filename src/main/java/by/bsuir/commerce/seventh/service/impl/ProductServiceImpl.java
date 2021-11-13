package by.bsuir.commerce.seventh.service.impl;

import by.bsuir.commerce.seventh.entity.Product;
import by.bsuir.commerce.seventh.repo.ProductRepo;
import by.bsuir.commerce.seventh.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import by.bsuir.commerce.seventh.entity.*;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo goodRepo){
        this.productRepo = goodRepo;
    }

    @Override
    public Optional<Product> save(Product good, MultipartFile[] files) {
        List<Image> images = good.getImages();
        for(MultipartFile file : files){
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
    public Product findById(long id) {
        return productRepo.findById(id);
    }

    @Override
    public List<Product> findTopSales() {
        return null;
    }

    @Override
    public List<Product> findNewest() {
        return null;
    }
}
