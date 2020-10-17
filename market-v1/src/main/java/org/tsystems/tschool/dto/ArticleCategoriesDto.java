package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Article and categories data transfer object
 * Class to access and control articles data
 */
@Setter
@Getter
public class ArticleCategoriesDto {

    private Long articleId;

    private List<ArticleCategoriesItemDto> current;

    private List<ArticleCategoriesItemDto> other;
}
