package org.tsystems.tschool.entity;

import lombok.*;
import org.tsystems.tschool.enums.DeliveryMethod;
import org.tsystems.tschool.enums.OrderStatus;
import org.tsystems.tschool.enums.PaymentMethod;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Order")
@Table(name = "user_order")
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "address_country")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code")),
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "house", column = @Column(name = "address_house")),
            @AttributeOverride(name = "flat", column = @Column(name = "address_flat"))
    })
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery")
    private DeliveryMethod deliveryMethod;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

    @NotNull
    @Column(name = "payment_status")
    private Boolean isPaid;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "price")
    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getUser().equals(order.getUser()) &&
                getAddress().equals(order.getAddress()) &&
                getPaymentMethod() == order.getPaymentMethod() &&
                getDeliveryMethod() == order.getDeliveryMethod() &&
                getIsPaid().equals(order.getIsPaid()) &&
                getOrderStatus() == order.getOrderStatus() &&
                getPrice().equals(order.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getAddress(), getPaymentMethod(), getDeliveryMethod(), getIsPaid(), getOrderStatus(), getPrice());
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

}
