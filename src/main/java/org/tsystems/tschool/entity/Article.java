package org.tsystems.tschool.entity;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"orderItem","cartItem","categories","values"})
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

    @NotNull(message = "Price cant be 0")
    @Column(name = "price")
    private Float price;

    @NotNull(message = "Quantity cant be 0")
    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderItem> orderItem = new HashSet<>();

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CartItem> cartItem = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST})
    @JoinTable(name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST})
    @JoinTable(name = "article_value",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "value_id")
    )
    Set<Value> values = new HashSet<>();

    public void addValue(Value value){
        values.add(value);
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public void removeValue(Value value){
        values.remove(value);
    }

    public void removeCategory(Category category){
        categories.remove(category);
    }
}
