package org.tsystems.tschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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


    private String title;

    private int price;

    private String category;

    private String weight;

    private String color;

    private int quantity;

    @OneToOne(mappedBy = "address")
    OrderItem orderItem;

    @OneToOne(mappedBy = "address")
    CartItem cartItem;
}
