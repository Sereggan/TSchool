package org.tsystems.tschool.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.CatalogArticleDto;
import org.tsystems.tschool.dto.CatalogValueDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-14T00:05:24+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 1.8.0_252 (Amazon.com Inc.)"
)
@Component
public class CatalogArticleDtoMapperImpl implements CatalogArticleDtoMapper {

    @Override
    public CatalogArticleDto articleToDto(Article article) {
        if ( article == null ) {
            return null;
        }

        CatalogArticleDto catalogArticleDto = new CatalogArticleDto();

        catalogArticleDto.setId( article.getId() );
        catalogArticleDto.setTitle( article.getTitle() );
        catalogArticleDto.setPrice( article.getPrice() );
        catalogArticleDto.setQuantity( article.getQuantity() );
        catalogArticleDto.setValues( valueSetToCatalogValueDtoList( article.getValues() ) );

        return catalogArticleDto;
    }

    @Override
    public CatalogValueDto valueToDto(Value value) {
        if ( value == null ) {
            return null;
        }

        CatalogValueDto catalogValueDto = new CatalogValueDto();

        catalogValueDto.setCategory( valueCategoryTitle( value ) );
        catalogValueDto.setId( value.getId() );
        catalogValueDto.setValue( value.getValue() );

        return catalogValueDto;
    }

    protected List<CatalogValueDto> valueSetToCatalogValueDtoList(Set<Value> set) {
        if ( set == null ) {
            return null;
        }

        List<CatalogValueDto> list = new ArrayList<CatalogValueDto>( set.size() );
        for ( Value value : set ) {
            list.add( valueToDto( value ) );
        }

        return list;
    }

    private String valueCategoryTitle(Value value) {
        if ( value == null ) {
            return null;
        }
        Category category = value.getCategory();
        if ( category == null ) {
            return null;
        }
        String title = category.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }
}
