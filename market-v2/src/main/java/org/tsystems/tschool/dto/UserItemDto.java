package org.tsystems.tschool.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Email
    @NotEmpty(message = "Email cant be empty")
    private String email;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
