package org.tsystems.tschool.service.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CategoryDAO;
import org.tsystems.tschool.dao.ValueDAO;
import org.tsystems.tschool.dto.*;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Value;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.mapper.CatalogArticleDtoMapper;
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

    private final CatalogArticleDtoMapper catalogArticleDtoMapper
            = Mappers.getMapper(CatalogArticleDtoMapper.class);

    public ArticleServiceImpl(ArticleDAO articleDAO, ValueDAO valueDAO, CategoryDAO categoryDAO) {
        this.articleDAO = articleDAO;
        this.valueDAO = valueDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleDAO.findALl().stream()
                .map(mapper::articleToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ArticleDto> findById(Long id) {
        return Optional.ofNullable(mapper.articleToDto(articleDAO.findArticleById(id)));
    }

    @Override
    public boolean removeArticleById(Long id) {
        return articleDAO.removeArticleById(id);
    }

    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        Article article =  articleDAO.saveArticle(mapper.dtoToArticle(articleDto));
        return mapper.articleToDto(article);
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        Article article = articleDAO.updateArticle(mapper.dtoToArticle(articleDto));
        return mapper.articleToDto(article);
    }

    @Override
    public ArticleCategoriesDto getAllCategoriesAndValuesByArticleId(Long articleId) {
        ArticleCategoriesDto categoriesDto = new ArticleCategoriesDto();

        categoriesDto.setArticleId(articleId);
        List<ArticleCategoriesItemDto> articleCategoriesDto = new ArrayList<>();
        Article article = articleDAO.findArticleById(articleId);
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
    public ArticleDto addValue(Long articleId, Long valueId) {
        Value value = valueDAO.findById(valueId);
        Article article = articleDAO.findArticleById(articleId);
        article.addValue(value);
        article.addCategory(value.getCategory());
        return mapper.articleToDto(articleDAO.saveArticle(article));
    }

    @Override
    public void deleteValue(Long articleId, Long valueId) {
        Value value = valueDAO.findById(valueId);
        Article article = articleDAO.findArticleById(articleId);
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

    @Override
    public CatalogDto getCatalog() {
        List<Article> articles = articleDAO.findALl();

        Set<CatalogArticleDto> articleDto = new HashSet<>();
        for(int i = 0;i<articles.size();i++){
            Article article = articles.get(i);
            CatalogArticleDto catalogArticleDto = catalogArticleDtoMapper.articleToDto(article);
            articleDto.add(catalogArticleDto);
        }

        CatalogDto catalogDto = new CatalogDto();
        catalogDto.setCatalogArticleDto(articleDto);
        return catalogDto;
    }
}
