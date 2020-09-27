package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
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

    private AddressDto addressDto;
}
