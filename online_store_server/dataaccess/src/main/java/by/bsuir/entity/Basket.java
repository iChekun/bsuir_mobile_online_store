package by.bsuir.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "baskets")
public class Basket extends AbstractEntity {

    @OneToOne(mappedBy = "basket")
    private User user;
    @ManyToMany(
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "basket_products",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> basketProducts = new ArrayList<>();

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice = BigDecimal.ZERO;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    public Basket() {
    }


    @PrePersist
    private void onCreate() {
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Product> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(List<Product> basketProducts) {
        this.basketProducts = basketProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

}
