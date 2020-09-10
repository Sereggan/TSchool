package org.tsystems.tschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    private Article article;

    @NotNull
    @Column(name = "cart_item_quantity")
    private int quantity;
}
