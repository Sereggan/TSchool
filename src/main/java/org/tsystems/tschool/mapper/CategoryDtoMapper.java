package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {
    CategoryDtoMapper INSTANCE = Mappers.getMapper( CategoryDtoMapper.class );

    CategoryDto categoryToDto(Category category);
    Category DtoToCategory(CategoryDto categoryDto);
}
