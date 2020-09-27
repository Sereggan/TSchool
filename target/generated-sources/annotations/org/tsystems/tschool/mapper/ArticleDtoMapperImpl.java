package org.tsystems.tschool.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-26T16:23:10+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 1.8.0_252 (Amazon.com Inc.)"
)
@Component
public class ArticleDtoMapperImpl implements ArticleDtoMapper {

    @Override
    public ArticleDto articleToDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleDto articleDto = new ArticleDto();

        articleDto.setId( article.getId() );
        articleDto.setTitle( article.getTitle() );
        articleDto.setPrice( article.getPrice() );
        articleDto.setQuantity( article.getQuantity() );

        return articleDto;
    }

    @Override
    public Article DtoToArticle(ArticleDto articleDto) {
        if ( articleDto == null ) {
            return null;
        }

        Article article = new Article();

        article.setId( articleDto.getId() );
        article.setTitle( articleDto.getTitle() );
        article.setPrice( articleDto.getPrice() );
        article.setQuantity( articleDto.getQuantity() );

        return article;
    }
}
