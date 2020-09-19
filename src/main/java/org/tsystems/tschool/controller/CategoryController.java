package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.CategoryDto;

import org.tsystems.tschool.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getAllCategoriesPage(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "categories/categoriesListPage";
    }

    @GetMapping("/add-category-page")
    public String getAddCategoryPage(CategoryDto categoryDto){
        return "categories/add-category-page";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("categoryDto") @Valid CategoryDto categoryDto, BindingResult result) {
        if (result.hasErrors()) {
            return "categories/add-category-page";
        }

        categoryService.saveCategory(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model, CategoryDto categoryDto){
        model.addAttribute("categoryDto", categoryService.findById(id));
        return "categories/edit-category-page";
    }
}
