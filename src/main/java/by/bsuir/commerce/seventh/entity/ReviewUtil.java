package by.bsuir.commerce.seventh.entity;

import java.util.List;
import java.util.OptionalDouble;

public final class ReviewUtil {
    static int averageRate(List<Review> reviews){
        OptionalDouble optionalDouble = reviews
                .stream()
                .map(Review::getRate)
                .mapToInt(Integer::intValue)
                .average();
        return (int) optionalDouble.orElse(0);
    }
}
