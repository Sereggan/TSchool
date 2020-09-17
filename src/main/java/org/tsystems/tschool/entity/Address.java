package org.tsystems.tschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {

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
