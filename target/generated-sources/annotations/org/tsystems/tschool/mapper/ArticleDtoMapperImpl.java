package org.tsystems.tschool.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-19T18:01:31+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
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
        articleDto.setCategories( categorySetToCategoryDtoSet( article.getCategories() ) );

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
        article.setCategories( categoryDtoSetToCategorySet( articleDto.getCategories() ) );

        return article;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setTitle( category.getTitle() );
        categoryDto.setDescription( category.getDescription() );

        return categoryDto;
    }

    protected Set<CategoryDto> categorySetToCategoryDtoSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDto> set1 = new HashSet<CategoryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryToCategoryDto( category ) );
        }

        return set1;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setTitle( categoryDto.getTitle() );
        category.setDescription( categoryDto.getDescription() );

        return category;
    }

    protected Set<Category> categoryDtoSetToCategorySet(Set<CategoryDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category> set1 = new HashSet<Category>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoryDto categoryDto : set ) {
            set1.add( categoryDtoToCategory( categoryDto ) );
        }

        return set1;
    }
}
