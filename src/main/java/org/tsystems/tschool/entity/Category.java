package org.tsystems.tschool.entity;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"articleSet","values"})
@Entity(name = "Category")
@Table(name = "category")
public class Category {

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
    Set<Article> articleSet = new HashSet<>();

    @OneToMany(mappedBy="category", fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Value> values = new HashSet<>();

    public void addValue(Value value) {
        values.add(value);
    }

    public void removeValue(Value value) {
        value.setCategory(null);
        values.remove(value);
    }
}
