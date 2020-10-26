package org.tsystems.tschool.service.jpa;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CategoryDAO;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.dao.ValueDAO;
import org.tsystems.tschool.dto.*;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.ArticleRating;
import org.tsystems.tschool.entity.Value;
import org.tsystems.tschool.exception.ArticleAlreadyExistException;
import org.tsystems.tschool.exception.ItemNotFoundException;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.mapper.CatalogArticleDtoMapper;
import org.tsystems.tschool.service.ArticleService;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of ArticleService interface
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    final ArticleDAO articleDAO;

    final ValueDAO valueDAO;

    final CategoryDAO categoryDAO;

    final OrderDAO orderDAO;

    private final ArticleDtoMapper mapper
            = Mappers.getMapper(ArticleDtoMapper.class);

    private final CatalogArticleDtoMapper catalogArticleDtoMapper
            = Mappers.getMapper(CatalogArticleDtoMapper.class);

    private static final Logger log = LogManager.getLogger(ArticleServiceImpl.class);

    private static final String ID_NOT_FOUND_MESSAGE = "Could not find article with such id: ";

    private static final String ARTICLE_DOESNT_EXIST_MESSAGE = "Could not find article with such id: ";

    public ArticleServiceImpl(ArticleDAO articleDAO, ValueDAO valueDAO, CategoryDAO categoryDAO, OrderDAO orderDAO) {
        this.articleDAO = articleDAO;
        this.valueDAO = valueDAO;
        this.categoryDAO = categoryDAO;
        this.orderDAO = orderDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ArticleDto> findAll() {
        List<ArticleDto> articleDtos = articleDAO.findAll().stream()
                .map(item -> {
                    ArticleDto articleDto = mapper.articleToDto(item);
                    articleDto.setIsActive(item.getCartItem().isEmpty());
                    return articleDto;
                })
                .collect(Collectors.toList());
        Collections.sort(articleDtos);
        return articleDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleDto findById(Long id) {
        Article article;
        try {
            article = articleDAO.findById(id);
        } catch (EmptyResultDataAccessException e) {
            log.info(ID_NOT_FOUND_MESSAGE + id);
            throw new ItemNotFoundException(ARTICLE_DOESNT_EXIST_MESSAGE);
        }
        return mapper.articleToDto(article);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeArticleById(Long id) {
        boolean isDeleted = false;
        try {
            isDeleted = articleDAO.removeById(id);
        } catch (EmptyResultDataAccessException e) {
            log.info(ID_NOT_FOUND_MESSAGE + id);
            throw new ItemNotFoundException(ARTICLE_DOESNT_EXIST_MESSAGE);
        }
        return isDeleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        Article article;
        try {
            article = articleDAO.save(mapper.dtoToArticle(articleDto));
        } catch (NonUniqueResultException e) {
            log.info("Created existing article");
            throw new ArticleAlreadyExistException();
        }
        return mapper.articleToDto(article);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        Article article;
        try {
            article = articleDAO.findByIdWithLock(articleDto.getId());
        } catch (EmptyResultDataAccessException e) {
            log.info(ID_NOT_FOUND_MESSAGE + articleDto.getId());
            throw new ItemNotFoundException(ARTICLE_DOESNT_EXIST_MESSAGE);
        }
        article.setQuantity(articleDto.getQuantity());
        article.setPrice(articleDto.getPrice());
        article.setTitle(articleDto.getTitle());
        return mapper.articleToDto(article);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleCategoriesDto getAllCategoriesAndValuesByArticleId(Long id) {
        ArticleCategoriesDto categoriesDto = new ArticleCategoriesDto();

        categoriesDto.setArticleId(id);
        List<ArticleCategoriesItemDto> articleCategoriesDto = new ArrayList<>();
        Article article;
        try {
            article = articleDAO.findById(id);
        } catch (EmptyResultDataAccessException e) {
            log.info(ID_NOT_FOUND_MESSAGE + id);
            throw new ItemNotFoundException(ARTICLE_DOESNT_EXIST_MESSAGE);
        }
        Set<Value> valueList = article.getValues();

        article.getValues().forEach(value -> articleCategoriesDto.add(
                new ArticleCategoriesItemDto(value.getId(), value.getCategory().getId(),
                        value.getTitle(), value.getCategory().getTitle())));

        categoriesDto.setCurrent(articleCategoriesDto);
        List<Value> allValues = valueDAO.findAll();
        List<Value> filteredValues = new ArrayList<>();

        for (Value value : allValues) {
            if (!valueList.contains(value)) {
                filteredValues.add(value);
            }
        }
        List<ArticleCategoriesItemDto> articleCategoriesDtoOther = new ArrayList<>();

        filteredValues.stream().forEach(value -> articleCategoriesDtoOther.add(
                new ArticleCategoriesItemDto(
                        value.getId(), value.getCategory().getId(),
                        value.getTitle(), value.getCategory().getTitle())));
        categoriesDto.setOther(articleCategoriesDtoOther);

        return categoriesDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArticleDto addValue(Long articleId, Long valueId) {
        Value value;
        try {
            value = valueDAO.findById(valueId);
        } catch (EmptyResultDataAccessException e) {
            log.info(ID_NOT_FOUND_MESSAGE + valueId);
            throw new ItemNotFoundException("Value doesnt exist");
        }
        Article article;
        try {
            article = articleDAO.findById(articleId);
        } catch (EmptyResultDataAccessException e) {
            log.info(ID_NOT_FOUND_MESSAGE + articleId);
            throw new ItemNotFoundException(ARTICLE_DOESNT_EXIST_MESSAGE);
        }
        article.addValue(value);
        article.addCategory(value.getCategory());
        return mapper.articleToDto(articleDAO.save(article));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteValue(Long articleId, Long valueId) {
        Value value;
        try {
            value = valueDAO.findById(valueId);
        } catch (EmptyResultDataAccessException e) {
            log.info("Could not find value with such id: " + valueId);
            throw new ItemNotFoundException("Value doesnt exist");
        }
        Article article;
        try {
            article = articleDAO.findById(articleId);
        } catch (EmptyResultDataAccessException e) {
            log.info(ID_NOT_FOUND_MESSAGE + articleId);
            throw new ItemNotFoundException(ARTICLE_DOESNT_EXIST_MESSAGE);
        }
        article.removeValue(value);
        boolean hasCategory = false; // Boolean to check if Article contains any other values of this category
        for (Value articleValue : article.getValues()) {
            if (articleValue.getCategory().getTitle().equals(value.getCategory().getTitle())) {
                hasCategory = true;
            }
        }
        if (!hasCategory) article.removeCategory(value.getCategory());  // Delete category if article doesnt contains
        // values of this category

        articleDAO.save(article);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CatalogDto getCatalog() {
        List<Article> articles = articleDAO.findAll();

        List<CatalogArticleDto> articleDto = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (article.getQuantity() > 0) {
                CatalogArticleDto catalogArticleDto = catalogArticleDtoMapper.articleToDto(article);
                articleDto.add(catalogArticleDto);
            }
        }
        Collections.sort(articleDto);

        CatalogDto catalogDto = new CatalogDto();
        catalogDto.setCatalogArticleDto(articleDto);
        return catalogDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ArticleRating> getArticlesRating() {
        return articleDAO.findBestSellers(10L);
    }
}
