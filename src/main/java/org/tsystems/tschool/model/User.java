package org.tsystems.tschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name cant't be empty")
    @Column(name = "user_name")
    private String name;

    @NotNull(message = "LastName cant't be empty")
    @Column(name = "user_lastName")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "user_birthday")
    private LocalDate birthday;

    @Email
    @NotNull(message = "Email cant't be empty")
    @Column(name = "user_email")
    private String email;

    @NotNull
    @Column(name = "user_password")
    private String password;

    @NotNull
    @Column(name = "user_role")
    private String role;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy="user")
    private List<Order> orders;
}
