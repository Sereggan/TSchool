package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.ArticleCategoriesDto;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.ArticleRating;
import org.tsystems.tschool.dto.CatalogDto;

import java.util.List;

/**
 * The interface Article to access and control Articles business logic.
 */
public interface ArticleService {

    /**
     * Find all articles.
     *
     * @return the list of all Articles
     */
    public List<ArticleDto> findAll();

    /**
     * Find article by id.
     *
     * @param id of article
     * @return the article dto
     */
    public ArticleDto findById(Long id);

    /**
     * Remove article by id
     *
     * @param id the id of article
     * @return the boolean if success
     */
    public boolean removeArticleById(Long id);

    /**
     * Save article
     *
     * @param articleDto article transfer object
     * @return the article transfer object
     */
    public ArticleDto saveArticle(ArticleDto articleDto);

    /**
     * Update article
     *
     * @param articleDto article transfer object
     * @return the article transfer object
     */
    public ArticleDto updateArticle(ArticleDto articleDto);

    /**
     * Gets all categories and values by article id.
     *
     * @param articleId the article id
     * @return the all categories and values by article id
     */
    public ArticleCategoriesDto getAllCategoriesAndValuesByArticleId(Long articleId);

    /**
     * Add value to article
     *
     * @param articleId the article id
     * @param valueId   the value id
     * @return the article dto
     */
    public ArticleDto addValue(Long articleId, Long valueId);

    /**
     * Delete value from article.
     *
     * @param articleId the article id
     * @param valueId   the value id
     */
    public void deleteValue(Long articleId, Long valueId);

    /**
     * Gets catalog.
     *
     * @return the catalog
     */
    public CatalogDto getCatalog();

    /**
     * Gets articles rating by total income
     *
     * @return the articles rating
     */
    public List<ArticleRating> getArticlesRating();

    /**
     * Gets articles rating by price
     *
     * @return the articles list
     */
    public List<ArticleDto> getTopArticles();

    public List<ArticleDto> findAllFiltered(String title, Float minPrice, Float maxPrice,
                                            Integer minQuantity, Integer maxQuantity);
}

