package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {
    ArticleDtoMapper INSTANCE = Mappers.getMapper( ArticleDtoMapper.class );

    ArticleDto articleToDto(Article article);
    Article DtoToArticle(ArticleDto articleDto);

}
