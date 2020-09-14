package org.tsystems.tschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Article")
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Title cant be empty")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Price cant be empty")
    @Column(name = "price")
    private Float price;

    @NotEmpty(message = "Category cant be empty")
    @Column(name = "category")
    private String category;

    @NotNull(message = "Weight cant be empty")
    @Column(name = "weight")
    private Float weight;

    @NotEmpty(message = "Color cant be empty")
    @Column(name = "color")
    private String color;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderItem> orderItem = new HashSet<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CartItem> cartItem = new HashSet<>();
}
