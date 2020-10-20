package org.tsystems.tschool.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Category and value data transfer object
 * Class to access and control category
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CategoryValueDto {

    @NotNull(message = "Category Id cant be null")
    private Long categoryId;

    @NotEmpty(message = "Title of value cant be empty")
    private String title;
}
