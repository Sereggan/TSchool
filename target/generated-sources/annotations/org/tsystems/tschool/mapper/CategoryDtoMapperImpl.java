package org.tsystems.tschool.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.entity.Category;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-19T18:01:31+0300",
    comments = "version: 1.4.0.CR1, compiler: javac, environment: Java 11.0.7 (JetBrains s.r.o.)"
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

        return categoryDto;
    }

    @Override
    public Category DtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setTitle( categoryDto.getTitle() );
        category.setDescription( categoryDto.getDescription() );

        return category;
    }
}
