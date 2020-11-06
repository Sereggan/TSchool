package org.tsystems.tschool.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.tsystems.tschool.entity.Authority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User data transfer object
 * Class to access and control user data
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserItemDto {

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

    private Set<Authority> roles = new HashSet<>();
}
