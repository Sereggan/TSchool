package org.tsystems.tschool.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-17T21:14:36+0300",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
)
@Component
public class ArticleDtoMapperImpl implements ArticleDtoMapper {

    @Override
    public ArticleDto articleToDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleDto articleDto = new ArticleDto();

        return articleDto;
    }

    @Override
    public Article DtoToArticle(ArticleDto articleDto) {
        if ( articleDto == null ) {
            return null;
        }

        Article article = new Article();

        return article;
    }
}
