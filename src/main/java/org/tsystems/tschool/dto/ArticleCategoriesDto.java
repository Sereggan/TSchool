package org.tsystems.tschool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class ArticleCategoriesDto {

    private Long articleId;

    private List<ArticleCategoriesItemDto> current;

    private List<ArticleCategoriesItemDto> other;
}