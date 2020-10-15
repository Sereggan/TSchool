package org.tsystems.tschool.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Order entity
 * Represent user's order item
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "Order_item")
@Table(name = "order_item")
public class OrderItem implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @NotNull
    @Column(name = "order_article_title")
    private String articleTitle;

    @NotNull
    @Column(name = "order_item_quantity")
    private Integer quantity;

    @NotNull
    @Column(name = "order_item_price")
    private Float price;
}
