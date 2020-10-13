package org.tsystems.tschool.mapper;

import org.mapstruct.Mapper;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    CategoryDto categoryToDto(Category category);

    Category dtoToCategory(CategoryDto categoryDto);
}
