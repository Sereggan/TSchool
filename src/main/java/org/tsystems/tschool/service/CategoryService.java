package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.dto.CategoryValueDto;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public List<CategoryDto> findAll();

    public Optional<CategoryDto> findById(Long id);

    public boolean removeCategoryById(Long id);

    public CategoryDto saveCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto);

    public void addValue(CategoryValueDto categoryValueDto);

    public void removeValue(Long valueId);
}
