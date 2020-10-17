package org.tsystems.tschool.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Cart entity
 * Represent user cart
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "Cart")
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    @NotNull
    @Min(value = 0, message = "Total cost cant be negative")
    @Column(name = "cart_totalcost")
    private Float totalCost;

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    /**
     * Remove item.
     *
     * @param item the item
     */
    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return getUser().equals(cart.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }
}
