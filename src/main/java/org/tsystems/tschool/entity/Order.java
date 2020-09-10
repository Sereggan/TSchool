package org.tsystems.tschool.entity;

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
@Table(name = "user_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @NotNull
    @Column(name = "payment")
    private String paymentMethod;

    @NotNull
    @Column(name = "delivery")
    private String deliveryMethod;

    @OneToMany(mappedBy="order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @NotNull
    @Column(name = "payment_status")
    private boolean isPaid;

    @NotNull
    @Column(name = "order_status")
    private String orderStatus;
}
