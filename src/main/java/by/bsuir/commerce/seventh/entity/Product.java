package by.bsuir.commerce.seventh.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9]*$", message = "Invalid name. Only letters and digits!")
    @Pattern(regexp = "^.{5,30}$", message = "Name must be between 5 and 30 characters!")
    private String name;

    @NotEmpty(message = "Description must not be empty")
    @Size(min=20 ,max = 255, message = "Description size is incorrect")
    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9.,;!?\"№%&]$", message = "Description contains invalid characters!")
    private String description;

    @Min(1)
    private BigDecimal price;

    private long sales;

    @NotEmpty(message = "Category must not be empty")
    private String category;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Image> images;

    @OneToMany()
    private List<Review> reviews;

    public Product() {
        images = new ArrayList<>();
    }

    public Product(long id, String name, String description, BigDecimal price, long sales,
                   List<Image> images, List<Review> reviews, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.sales = sales;
        this.images = images;
        this.reviews = reviews;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (sales != product.sales) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (images != null ? !images.equals(product.images) : product.images != null) return false;
        return reviews != null ? reviews.equals(product.reviews) : product.reviews == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (sales ^ (sales >>> 32));
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", images=" + images +
                ", reviews=" + reviews +
                '}';
    }
}
