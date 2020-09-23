package org.tsystems.tschool.service.HibernateJPA;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CategoryDAO;
import org.tsystems.tschool.dao.ValueDAO;
import org.tsystems.tschool.dto.ArticleCategoriesDto;
import org.tsystems.tschool.dto.ArticleCategoriesItemDto;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.OrderItem;
import org.tsystems.tschool.entity.Value;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.service.ArticleService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    final ArticleDAO articleDAO;

    final ValueDAO valueDAO;

    final CategoryDAO categoryDAO;

    private final ArticleDtoMapper mapper
            = Mappers.getMapper(ArticleDtoMapper.class);

    public ArticleServiceImpl(ArticleDAO articleDAO, ValueDAO valueDAO, CategoryDAO categoryDAO, CategoryDAO categoryDAO1) {
        this.articleDAO = articleDAO;
        this.valueDAO = valueDAO;
        this.categoryDAO = categoryDAO1;
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleDAO.getAllArticles().stream()
                .map(article-> mapper.articleToDto((Article)article))
                .collect(Collectors.toList());
    }

    @Override
    public List findAllByOrderItem(OrderItem orderItem) {
        return articleDAO.getAllArticlesByOrderItem(orderItem);
    }

    @Override
    public Optional<ArticleDto> findById(Long id) {
        return Optional.ofNullable(mapper.articleToDto(articleDAO.getArticleById(id)));
    }

    @Override
    public void removeArticleById(Long id) {
        articleDAO.removeArticleById(id);
    }

    @Override
    public void saveArticle(ArticleDto articleDto) {
        articleDAO.saveArticle(mapper.DtoToArticle(articleDto));
    }

    @Override
    public void updateArticle(ArticleDto articleDto) {
        articleDAO.updateArticle(mapper.DtoToArticle(articleDto));
    }

    @Override
    public ArticleCategoriesDto getAllCategoriesAndValuesByArticleId(Long articleId) {
        ArticleCategoriesDto categoriesDto = new ArticleCategoriesDto();

        categoriesDto.setArticleId(articleId);

        List<ArticleCategoriesItemDto> articleCategoriesDto = new ArrayList<>();

        Article article = articleDAO.getArticleById(articleId);

        Set<Value> valueList = article.getValues();

        article.getValues().forEach(value->articleCategoriesDto.add(
                new ArticleCategoriesItemDto( value.getId(), value.getCategory().getId(),
                        value.getValue(),value.getCategory().getTitle())));

        categoriesDto.setCurrent(articleCategoriesDto);

        List<Value> allValues = valueDAO.findAll();

        List<Value> filteredValues = new ArrayList<>();

        for(Value value: allValues){
            if(!valueList.contains(value)){
                filteredValues.add(value);
            }
        }

        List<ArticleCategoriesItemDto> articleCategoriesDtoOther = new ArrayList<>();

        filteredValues.stream().forEach(value ->  articleCategoriesDtoOther.add(
                new ArticleCategoriesItemDto(
                        value.getId(), value.getCategory().getId(),
                        value.getValue(),value.getCategory().getTitle())));

        categoriesDto.setOther(articleCategoriesDtoOther);

        return categoriesDto;
    }

    @Override
    public void addValue(Long articleId, Long valueId) {
        Value value = valueDAO.findById(valueId);
        Article article = articleDAO.getArticleById(articleId);
        article.addValue(value);
        article.addCategory(value.getCategory());
        articleDAO.saveArticle(article);
    }

    @Override
    public void deleteValue(Long articleId, Long valueId) {
        Value value = valueDAO.findById(valueId);
        Article article = articleDAO.getArticleById(articleId);
        article.removeValue(value);
        boolean hasCategory = false;
        for(Value articleValue: article.getValues()){
            if(articleValue.getCategory().getTitle().equals(value.getCategory().getTitle())){
                hasCategory = true;
            }
        }
        if(!hasCategory)  article.removeCategory(value.getCategory());

        articleDAO.saveArticle(article);
    }
}
