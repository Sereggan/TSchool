package org.tsystems.tschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "price")
    private int price;

    @NotNull
    @Column(name = "category")
    private String category;

    @NotNull
    @Column(name = "weight")
    private String weight;

    @NotNull
    @Column(name = "color")
    private String color;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderItem> orderItem = new HashSet<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CartItem> cartItem = new HashSet<>();
}
