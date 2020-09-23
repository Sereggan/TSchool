package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class ArticleCategoriesItemDto {

    @NotNull
    private Long valueId;

    @NotNull
    private Long categoryId;

    private String value;

    private String category;

}
