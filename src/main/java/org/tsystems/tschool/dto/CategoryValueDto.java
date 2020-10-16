package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Category and value data transfer object
 * Class to access and control category
 */
@Getter
@Setter
public class CategoryValueDto {

    @NotNull(message = "Category Id cant be null")
    private Long categoryId;

    @NotEmpty(message = "Title of value cant be empty")
    private String title;
}
