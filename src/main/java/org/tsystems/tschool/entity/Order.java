package org.tsystems.tschool.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity(name = "Order")
@Table(name = "user_order")
public class Order implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "address_country")),
            @AttributeOverride( name = "city", column = @Column(name = "address_city")),
            @AttributeOverride( name = "postalCode", column = @Column(name = "address_postal_code")),
            @AttributeOverride( name = "street", column = @Column(name = "address_street")),
            @AttributeOverride( name = "house", column = @Column(name = "address_house")),
            @AttributeOverride( name = "flat", column = @Column(name = "address_flat"))
    })
    private Address address;

    @NotNull
    @Column(name = "payment_method")
    private String paymentMethod;

    @NotNull
    @Column(name = "delivery")
    private String deliveryMethod;

    @OneToMany(mappedBy="order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @NotNull
    @Column(name = "payment_status")
    private Boolean isPaid;

    @NotNull
    @Column(name = "order_status")
    private String orderStatus;


}
