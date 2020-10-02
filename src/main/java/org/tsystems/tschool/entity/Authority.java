package org.tsystems.tschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tsystems.tschool.enums.AuthorityType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AuthorityType role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}