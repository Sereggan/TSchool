package org.tsystems.tschool.service.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CategoryDAO;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.dao.ValueDAO;
import org.tsystems.tschool.dto.*;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.ArticleRatingDto;
import org.tsystems.tschool.entity.Value;
import org.tsystems.tschool.exception.ItemNotFoundException;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.mapper.CatalogArticleDtoMapper;
import org.tsystems.tschool.service.ArticleService;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

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

    public ArticleServiceImpl(ArticleDAO articleDAO, ValueDAO valueDAO, CategoryDAO categoryDAO, OrderDAO orderDAO) {
        this.articleDAO = articleDAO;
        this.valueDAO = valueDAO;
        this.categoryDAO = categoryDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public List<ArticleDto> findAll() {
        List<ArticleDto> articleDtos = articleDAO.findAll().stream()
                .map(item -> {
                    ArticleDto articleDto = mapper.articleToDto(item);
                    if (item.getCartItem().isEmpty()) {
                        articleDto.setIsActive(true);
                    }
                    return articleDto;
                })
                .collect(Collectors.toList());
        Collections.sort(articleDtos);
        return articleDtos;
    }

    @Override
    public ArticleDto findById(Long id) {
        Article article;
        try {
            article = articleDAO.findById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ItemNotFoundException("Article doesnt exist");
        }

        return mapper.articleToDto(article);
    }

    @Override
    public boolean removeArticleById(Long id) {
        return articleDAO.removeById(id);
    }

    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        Article article = articleDAO.save(mapper.dtoToArticle(articleDto));
        return mapper.articleToDto(article);
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        Article article = articleDAO.update(mapper.dtoToArticle(articleDto));
        return mapper.articleToDto(article);
    }

    @Override
    public ArticleCategoriesDto getAllCategoriesAndValuesByArticleId(Long id) {
        ArticleCategoriesDto categoriesDto = new ArticleCategoriesDto();

        categoriesDto.setArticleId(id);
        List<ArticleCategoriesItemDto> articleCategoriesDto = new ArrayList<>();
        Article article;
        try {
            article = articleDAO.findById(id);
        }catch (NoResultException e){
            throw new ItemNotFoundException("Article doesnt exist");
        }
        Set<Value> valueList = article.getValues();

        article.getValues().forEach(value -> articleCategoriesDto.add(
                new ArticleCategoriesItemDto(value.getId(), value.getCategory().getId(),
                        value.getValue(), value.getCategory().getTitle())));

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
                        value.getValue(), value.getCategory().getTitle())));
        categoriesDto.setOther(articleCategoriesDtoOther);

        return categoriesDto;
    }

    @Override
    public ArticleDto addValue(Long articleId, Long valueId) {
        Value value;
        try {
            value = valueDAO.findById(valueId);
        }catch (NoResultException e){
            throw new ItemNotFoundException("Value doesnt exist");
        }
        Article article;
        try {
            article = articleDAO.findById(articleId);
        }catch (NoResultException e){
            throw new ItemNotFoundException("Article doesnt exist");
        }
        article.addValue(value);
        article.addCategory(value.getCategory());
        return mapper.articleToDto(articleDAO.save(article));
    }

    @Override
    public void deleteValue(Long articleId, Long valueId) {
        Value value;
        try {
            value = valueDAO.findById(valueId);
        }catch (NoResultException e){
            throw new ItemNotFoundException("Value doesnt exist");
        }
        Article article;
        try {
            article = articleDAO.findById(articleId);
        }catch (NoResultException e){
            throw new ItemNotFoundException("Article doesnt exist");
        }
        article.removeValue(value);
        boolean hasCategory = false;
        for (Value articleValue : article.getValues()) {
            if (articleValue.getCategory().getTitle().equals(value.getCategory().getTitle())) {
                hasCategory = true;
            }
        }
        if (!hasCategory) article.removeCategory(value.getCategory());

        articleDAO.save(article);
    }

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

    @Override
    public List<ArticleRatingDto> getArticlesRating() {
        return articleDAO.findBestSellers(10L);
    }
}
