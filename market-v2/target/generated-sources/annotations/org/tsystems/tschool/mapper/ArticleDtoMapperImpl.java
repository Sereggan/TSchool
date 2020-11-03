package org.tsystems.tschool.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.ArticleDto.ArticleDtoBuilder;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Article.ArticleBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-03T23:19:31+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class ArticleDtoMapperImpl implements ArticleDtoMapper {

    @Override
    public ArticleDto articleToDto(Article article) {
        if ( article == null ) {
            return null;
        }

        ArticleDtoBuilder articleDto = ArticleDto.builder();

        articleDto.id( article.getId() );
        articleDto.title( article.getTitle() );
        articleDto.price( article.getPrice() );
        articleDto.quantity( article.getQuantity() );

        return articleDto.build();
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
