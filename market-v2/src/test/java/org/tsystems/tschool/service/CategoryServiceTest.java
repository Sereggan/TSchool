package org.tsystems.tschool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CategoryDAO;
import org.tsystems.tschool.dao.ValueDAO;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.dto.CategoryValueDto;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;
import org.tsystems.tschool.service.jpa.CategoryServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    CategoryDAO categoryDAO;

    @Mock
    private ValueDAO valueDAO;

    @Mock
    private ArticleDAO articleDAO;

    @InjectMocks
    CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder().id(1L).description("Category1").title("Category1").build();
    }

    @Test
    void findAll() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        categoryList.add(Category.builder().id(2L).description("Category2").title("Category2").build());
        when(categoryDAO.findAll()).thenReturn(categoryList);
        List<CategoryDto> result = categoryService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void findAllEmptyResult() {
        List<Category> categoryList = new ArrayList<>();
        when(categoryDAO.findAll()).thenReturn(categoryList);
        List<CategoryDto> result = categoryService.findAll();
        assertNotNull(result);
    }

    @DisplayName("Find by existing id")
    @Test
    void findById() {
        when(categoryDAO.findById(any(Long.class))).thenReturn(category);
        CategoryDto result = categoryService.findById(1L).get();
        assertNotNull(result);
    }

    @DisplayName("Find by wrong id")
    @Test
    void findByWrongId() {
        when(categoryDAO.findById(any(Long.class))).thenReturn(category);
        Optional<CategoryDto> result = categoryService.findById(1L);
        assertNotNull(result);
    }

    @Test
    void removeCategoryById() {
        category.setValues(new HashSet<>());
        when(categoryDAO.findById(any(Long.class))).thenReturn(category);
        when(articleDAO.findByCategoryId(any(Long.class))).thenReturn(new HashSet<>());
        categoryService.removeCategoryById(1L);
        verify(categoryDAO, times(1)).remove(category);
    }

    @Test
    void saveCategory() {
        categoryService.saveCategory(CategoryDto.builder().build());
        verify(categoryDAO, times(1)).save(any(Category.class));
    }

    @Test
    void updateCategory() {
        categoryService.updateCategory(CategoryDto.builder().build());
        verify(categoryDAO, times(1)).update(any(Category.class));
    }

    @Test
    void addValue() {
        categoryService.addValue(CategoryValueDto.builder().title("Title").categoryId(1L).build());
        verify(valueDAO, times(1)).addValue(any(Value.class), any(Long.class));
    }

    @Test
    void removeValue() {
        categoryService.removeValue(any(Long.class));
        verify(valueDAO, times(1)).removeValueFromCategory(anyLong());
    }
}