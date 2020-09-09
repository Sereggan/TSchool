package org.tsystems.tschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;

    @NotNull
    @Column(name = "delivery_method")
    private String deliveryMethod;

    @OneToMany(mappedBy="order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @NotNull
    @Column(name = "payment_status")
    private boolean isPaid;

    @NotNull
    @Column(name = "order_status")
    private String orderStatus;
}
