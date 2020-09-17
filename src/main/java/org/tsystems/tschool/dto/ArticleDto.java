package org.tsystems.tschool.dto;

import org.tsystems.tschool.entity.Category;

import java.util.Set;

public class ArticleDto {

    private int id;

    private String title;

    private Float price;

    private Integer quantity;

    private Set<Category> categories;
}
