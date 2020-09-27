package org.tsystems.tschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name cant't be empty")
    @Column(name = "user_name", unique = true)
    private String username;

    @NotNull(message = "LastName cant't be empty")
    @Column(name = "user_lastName")
    private String lastName;

    @Basic
    @Column(name = "user_birthday")
    private LocalDate birthday;

    @Email
    @NotNull(message = "Email cant't be empty")
    @Column(name = "user_email", unique = true)
    private String email;

    @NotNull
    @Column(name = "user_password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_authority",
            joinColumns = {
                    @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_id")})
    private Set<Authority> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy="user" , cascade = CascadeType.ALL)
    private List<Order> orders;
}
