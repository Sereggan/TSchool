package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
public class UserDto {

    private Long id;

    @NotNull(message = "Name cant be empty")
    private String username;

    @NotNull(message = "LastName cant't be empty")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Email
    @NotNull(message = "Email cant be empty")
    private String email;

    private AddressDto addressDto;
}
