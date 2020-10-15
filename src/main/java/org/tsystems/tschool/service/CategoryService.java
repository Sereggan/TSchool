package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.dto.CategoryValueDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface Category service.
 */
public interface CategoryService {

    /**
     * Find all categories.
     *
     * @return the list of categories
     */
    public List<CategoryDto> findAll();

    /**
     * Find by id category.
     *
     * @param id the id of category
     * @return the category transfer object
     */
    public Optional<CategoryDto> findById(Long id);

    /**
     * Remove category by id.
     *
     * @param id the category id
     * @return the boolean if success
     */
    public boolean removeCategoryById(Long id);

    /**
     * Save category category transfer object.
     *
     * @param categoryDto the category transfer object
     * @return the category transfer object
     */
    public CategoryDto saveCategory(CategoryDto categoryDto);

    /**
     * Update category category transfer object.
     *
     * @param categoryDto the category transfer object
     * @return the category transfer object
     */
    public CategoryDto updateCategory(CategoryDto categoryDto);

    /**
     * Add value.
     *
     * @param categoryValueDto the category value transfer object
     */
    public void addValue(CategoryValueDto categoryValueDto);

    /**
     * Remove value.
     *
     * @param valueId the value id
     */
    public void removeValue(Long valueId);
}
