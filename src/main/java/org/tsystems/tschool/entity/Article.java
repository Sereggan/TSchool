package org.tsystems.tschool.entity;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The class representing Article
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "Article")
@Table(name = "article")
public class Article implements Serializable  {

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
    @Min(value = 0, message = "Quantity cant be Negative")
    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<CartItem> cartItem = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "article_category",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "article_value",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "value_id")
    )
    private Set<Value> values = new HashSet<>();

    @Version
    private long version = 0L;

    /**
     * Adds value to values
     *
     * @param value the value
     */
    public void addValue(Value value) {
        values.add(value);
    }

    /**
     * Adds category to categories
     *
     * @param category the category
     */
    public void addCategory(Category category) {
        categories.add(category);
    }

    /**
     * Removes value from values
     *
     * @param value the value
     */
    public void removeValue(Value value) {
        values.remove(value);
        value.getArticles().remove(this);
    }

    /**
     * Removes category from categories
     *
     * @param category the category
     */
    public void removeCategory(Category category) {
        categories.remove(category);
        category.getArticleSet().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return getTitle().equals(article.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
}
