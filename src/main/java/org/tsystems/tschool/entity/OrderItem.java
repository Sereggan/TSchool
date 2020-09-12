package org.tsystems.tschool.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Order_item")
@EqualsAndHashCode()
@Table(name = "order_item")
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @NotNull
    @Column(name = "order_item_quantity")
    private int quantity;
}
