package by.bsuir.commerce.seventh.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String description;

    @OneToMany
    private List<Equipment> equipments;

    @OneToMany()
    private List<Review> reviews;

    public Good() {
    }

    public Good(long id, String name, String description, List<Equipment> equipments, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.equipments = equipments;
        this.reviews = reviews;
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

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        if (id != good.id) return false;
        if (name != null ? !name.equals(good.name) : good.name != null) return false;
        if (description != null ? !description.equals(good.description) : good.description != null) return false;
        if (equipments != null ? !equipments.equals(good.equipments) : good.equipments != null) return false;
        return reviews != null ? reviews.equals(good.reviews) : good.reviews == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (equipments != null ? equipments.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", equipments=" + equipments +
                ", reviews=" + reviews +
                '}';
    }
}
