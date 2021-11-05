package by.bsuir.commerce.seventh.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<Good> goods;

    public Cart(){}

    public Cart(long id, List<Good> goods) {
        this.id = id;
        this.goods = goods;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (id != cart.id) return false;
        return goods != null ? goods.equals(cart.goods) : cart.goods == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (goods != null ? goods.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", goods=" + goods +
                '}';
    }
}
