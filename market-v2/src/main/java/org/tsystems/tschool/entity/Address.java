package org.tsystems.tschool.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The class representing address.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
