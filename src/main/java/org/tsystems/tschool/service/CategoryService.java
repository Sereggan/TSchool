package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.OrderItem;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public List findAll();

    public List findAllByOrderItem(CategoryDto orderItem);

    public Optional<CategoryDto> findById(Long id);

    public void removeCategoryById(Long id);

    public void saveCategory(CategoryDto categoryDto);
}
