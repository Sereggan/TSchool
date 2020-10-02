package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.tsystems.tschool.dto.CatalogArticleDto;
import org.tsystems.tschool.dto.CatalogValueDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Value;

@Mapper(componentModel = "spring")
public interface CatalogArticleDtoMapper {

    CatalogArticleDtoMapper INSTANCE = Mappers.getMapper( CatalogArticleDtoMapper.class );

    CatalogArticleDto articleToDto(Article article);

    @Mapping(source = "value.category.title", target = "category")
    CatalogValueDto valueToDto(Value value);
}
