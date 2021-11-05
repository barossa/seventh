package by.bsuir.commerce.seventh.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Good good;

    private String description;

    private BigDecimal price;

    private long quantity;

    public Equipment() {}

    public Equipment(long id, Good good, String description, BigDecimal price, long quantity) {
        this.id = id;
        this.good = good;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipment equipment = (Equipment) o;

        if (id != equipment.id) return false;
        if (quantity != equipment.quantity) return false;
        if (good != null ? !good.equals(equipment.good) : equipment.good != null) return false;
        if (description != null ? !description.equals(equipment.description) : equipment.description != null)
            return false;
        return price != null ? price.equals(equipment.price) : equipment.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (good != null ? good.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", good=" + good +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
