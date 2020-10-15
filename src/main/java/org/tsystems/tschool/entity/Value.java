package org.tsystems.tschool.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Value entity
 * Represent value in category
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"articles"})
@Entity(name = "Value")
@Table(name = "value")
public class Value implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToMany(mappedBy = "values")
    private Set<Article> articles = new HashSet<>();

    /**
     * Remove article.
     *
     * @param article the article
     */
    public void removeArticle(Article article) {
        articles.remove(article);
        article.getValues().remove(this);
    }
}
