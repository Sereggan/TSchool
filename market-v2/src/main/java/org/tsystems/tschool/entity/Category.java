package org.tsystems.tschool.entity;

import lombok.*;
import javax.validation.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Category entity
 * Represent articles category
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"articleSet", "values"})
@Entity(name = "Category")
@Table(name = "category")
public class Category implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Title cant be empty")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Description cant be empty")
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Article> articleSet = new HashSet<>();

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    private Set<Value> values = new HashSet<>();

    /**
     * Add value.
     *
     * @param value the value
     */
    public void addValue(Value value) {
        values.add(value);
    }

    /**
     * Remove value.
     *
     * @param value the value
     */
    public void removeValue(Value value) {
        value.setCategory(null);
        values.remove(value);
    }
}
