package org.tsystems.tschool.entity;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "User")
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name cant be empty")
    @Column(name = "user_name", unique = true, updatable = false)
    private String username;

    @NotNull(message = "LastName cant be empty")
    @Column(name = "user_lastName")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "user_birthday")
    private Date birthday;

    @Email
    @NotNull(message = "Email cant be empty")
    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "user_password")
    private String password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "user_country")),
            @AttributeOverride( name = "city", column = @Column(name = "user_city")),
            @AttributeOverride( name = "postalCode", column = @Column(name = "user_postal_code")),
            @AttributeOverride( name = "street", column = @Column(name = "user_street")),
            @AttributeOverride( name = "house", column = @Column(name = "user_house")),
            @AttributeOverride( name = "flat", column = @Column(name = "user_flat"))
    })
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_authority",
            joinColumns = {
                    @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "authority_id")})
    private Set<Authority> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Cart cart;

    @OneToMany(mappedBy="user" , cascade ={CascadeType.MERGE, CascadeType.REMOVE})
    private List<Order> orders;
}
