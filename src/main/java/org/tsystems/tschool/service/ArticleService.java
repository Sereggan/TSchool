package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.ArticleCategoriesDto;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.ArticleRatingDto;
import org.tsystems.tschool.dto.CatalogDto;

import java.util.List;

public interface ArticleService {

    public List<ArticleDto> findAll();

    public ArticleDto findById(Long id);

    public boolean removeArticleById(Long id);

    public ArticleDto saveArticle(ArticleDto articleDto);

    public ArticleDto updateArticle(ArticleDto articleDto);

    public ArticleCategoriesDto getAllCategoriesAndValuesByArticleId(Long articleId);

    public ArticleDto addValue(Long articleId, Long valueId);

    public void deleteValue(Long articleId, Long valueId);

    public CatalogDto getCatalog();

    public List<ArticleRatingDto> getArticlesRating();
}

