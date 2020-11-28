package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tsystems.tschool.dto.CategoryDto;

import org.tsystems.tschool.dto.CategoryValueDto;
import org.tsystems.tschool.service.CategoryService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private static final String REDIRECT = "redirect:";
    private static final String CATEGORIES_URL = "/categories";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getAllCategoriesPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/categoriesListPage";
    }

    @GetMapping("/add-category-page")
    public String getAddCategoryPage(CategoryDto categoryDto) {
        return "categories/addCategoryPage";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("categoryDto") @Valid CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/addCategoryPage";
        }
        categoryService.saveCategory(categoryDto);
        return REDIRECT + CATEGORIES_URL;
    }

    @GetMapping("/edit/{id}")
    public String editCategoryPage(@PathVariable Long id, Model model) {
        Optional<CategoryDto> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("categoryDto", category.get());
            return "categories/editCategoryPage";
        } else {
            return REDIRECT + CATEGORIES_URL;
        }
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, @ModelAttribute("categoryDto") @Valid CategoryDto categoryDto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "categories/editCategoryPage";
        }
        if (!categoryService.findById(id).isPresent()) {
            return REDIRECT + CATEGORIES_URL;
        }
        categoryService.updateCategory(categoryDto);
        return REDIRECT + CATEGORIES_URL;
    }

    @GetMapping("/delete/{id}")
    public String deleteCategoryById(@PathVariable Long id) {
        categoryService.removeCategoryById(id);
        return REDIRECT + CATEGORIES_URL;
    }

    @GetMapping("/values/{id}")
    public String getCategoryValues(@PathVariable Long id, Model model) {
        Optional<CategoryDto> category = categoryService.findById(id);
        if (category.isPresent()) {
            model.addAttribute("categoryValueDto", new CategoryValueDto());
            model.addAttribute("categoryDto", category.get());
            return "categories/category-values-page";
        } else {
            return REDIRECT + CATEGORIES_URL;
        }
    }

    @PostMapping("/values/add/{id}")
    public String editCategory(@PathVariable Long id,
                               @ModelAttribute("categoryValueDto") @Valid CategoryValueDto categoryValueDto,
                               BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return REDIRECT + "/categories/values/{id}";
        }
        categoryService.addValue(categoryValueDto);
        return REDIRECT + "/categories/values/{id}";
    }

    @GetMapping("/values/delete/{id}")
    public String deleteValueById(@PathVariable Long id) {
        categoryService.removeValue(id);
        return REDIRECT + CATEGORIES_URL;
    }
}
