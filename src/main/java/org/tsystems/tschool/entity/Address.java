package org.tsystems.tschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The class representing address.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address implements Serializable {

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private Integer postalCode;

    @Column
    private String street;

    @Column
    private String house;

    @Column
    private Integer flat;
}
