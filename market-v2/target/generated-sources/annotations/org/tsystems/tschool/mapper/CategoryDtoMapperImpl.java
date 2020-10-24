package org.tsystems.tschool.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.dto.CategoryDto.CategoryDtoBuilder;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Category.CategoryBuilder;
import org.tsystems.tschool.entity.Value;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-23T22:23:12+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@Component
public class CategoryDtoMapperImpl implements CategoryDtoMapper {

    @Override
    public CategoryDto categoryToDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.title( category.getTitle() );
        categoryDto.description( category.getDescription() );
        Set<Value> set = category.getValues();
        if ( set != null ) {
            categoryDto.values( new HashSet<Value>( set ) );
        }

        return categoryDto.build();
    }

    @Override
    public Category dtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        CategoryBuilder category = Category.builder();

        category.id( categoryDto.getId() );
        category.title( categoryDto.getTitle() );
        category.description( categoryDto.getDescription() );
        Set<Value> set = categoryDto.getValues();
        if ( set != null ) {
            category.values( new HashSet<Value>( set ) );
        }

        return category.build();
    }
}
