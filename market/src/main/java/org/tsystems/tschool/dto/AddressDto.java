package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Address data transfer object
 * Class to access and control user data
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto {

    private String country;

    private String city;

    private Integer postalCode;

    private String street;

    private String house;

    private Integer flat;
}
