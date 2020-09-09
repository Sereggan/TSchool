package org.tsystems.tschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="address")
public class Address {

    private String country;

    private String city;

    private int postalCode;

    private String street;

    private String house;

    private int flat;
}
