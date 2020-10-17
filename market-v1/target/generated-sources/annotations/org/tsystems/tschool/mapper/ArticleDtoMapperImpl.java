package org.tsystems.tschool.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Article.ArticleBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-16T19:52:42+0300",
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
    public Article dtoToArticle(ArticleDto articleDto) {
        if ( articleDto == null ) {
            return null;
        }

        ArticleBuilder article = Article.builder();

        article.id( articleDto.getId() );
        article.title( articleDto.getTitle() );
        article.price( articleDto.getPrice() );
        article.quantity( articleDto.getQuantity() );

        return article.build();
    }
}
