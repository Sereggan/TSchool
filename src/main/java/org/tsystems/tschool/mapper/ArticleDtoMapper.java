package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {

    ArticleDto articleToDto(Article article);

    Article dtoToArticle(ArticleDto articleDto);

}
