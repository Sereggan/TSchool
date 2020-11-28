package org.tsystems.tschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Article and categories data transfer object
 * Class to access and control articles data
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoriesDto {

    private Long articleId;

    private List<ArticleCategoriesItemDto> current;

    private List<ArticleCategoriesItemDto> other;
}
