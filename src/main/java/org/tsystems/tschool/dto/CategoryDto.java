package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Setter
@Getter
public class CategoryDto {

    private Long id;

    @NotEmpty(message = "Title cant be empty")
    private String title;

    @NotEmpty(message = "Description cant be empty")
    private String description;
}
