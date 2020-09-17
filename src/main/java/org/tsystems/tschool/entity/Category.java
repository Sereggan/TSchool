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
@Entity(name = "Category")
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "name cant be null")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Description cant be null")
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "categories")
    Set<Article> articleSet = new HashSet<>();
}
