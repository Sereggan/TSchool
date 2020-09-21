package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryValueDto {

    @NotNull(message = "Category Id cant be null")
    private Long categoryId;

    @NotEmpty(message = "Value cant be empty")
    private String value;
}
