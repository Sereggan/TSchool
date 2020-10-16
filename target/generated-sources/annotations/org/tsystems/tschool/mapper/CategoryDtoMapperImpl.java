package org.tsystems.tschool.mapper;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-16T19:52:42+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 1.8.0_252 (Amazon.com Inc.)"
)
@Component
public class CategoryDtoMapperImpl implements CategoryDtoMapper {

    @Override
    public CategoryDto categoryToDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setTitle( category.getTitle() );
        categoryDto.setDescription( category.getDescription() );
        Set<Value> set = category.getValues();
        if ( set != null ) {
            categoryDto.setValues( new HashSet<Value>( set ) );
        }

        return categoryDto;
    }

    @Override
    public Category dtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setTitle( categoryDto.getTitle() );
        category.setDescription( categoryDto.getDescription() );
        Set<Value> set = categoryDto.getValues();
        if ( set != null ) {
            category.setValues( new HashSet<Value>( set ) );
        }

        return category;
    }
}
