package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Category and value data transfer object
 * Class to access and control category
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryValueDto {

    @NotNull(message = "Category Id cant be null")
    private Long categoryId;

    @NotEmpty(message = "Title of value cant be empty")
    private String title;
}
