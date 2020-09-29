package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
public class UserDto {

    @NotNull
    private Long id;

    @NotEmpty(message = "Name cant be empty")
    private String username;

    @NotEmpty(message = "LastName cant be empty")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @Email
    @NotEmpty(message = "Email cant be empty")
    private String email;

    private AddressDto addressDto;
}
