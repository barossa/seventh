package by.bsuir.commerce.seventh.service.util;

import by.bsuir.commerce.seventh.entity.Product;

import java.util.List;

public final class ProductUtils {
    public static List<Product> removeFirstOccurrence(List<Product> products, long productId){
        for (int i = 0; i < products.size(); i++) {
            Product current = products.get(i);
            if(current.getId() == productId){
                products.remove(i);
                break;
            }
        }
        return products;
    }
}
