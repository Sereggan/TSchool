package org.tsystems.tschool.dto;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UserDto {

    private Long id;

    @NotNull(message = "Name cant be empty")
    private String username;

    @NotNull(message = "LastName cant't be empty")
    private String lastName;

    @Basic
    private LocalDate birthday;

    @Email
    @NotNull(message = "Email cant be empty")
    private String email;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private Integer postalCode;

    @NotNull
    private String street;

    @NotNull
    private String house;

    @NotNull
    private Integer flat;
}
