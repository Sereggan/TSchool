package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Articles and categories data transfer object
 * Class to access and control article
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoriesItemDto {

    @NotNull
    private Long valueId;

    @NotNull
    private Long categoryId;

    private String value;

    private String category;

}
