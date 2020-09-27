package org.tsystems.tschool.service.HibernateJPA;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dao.CategoryDAO;
import org.tsystems.tschool.dao.ValueDAO;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.dto.CategoryValueDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;
import org.tsystems.tschool.mapper.CategoryDtoMapper;
import org.tsystems.tschool.service.CategoryService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    private final ValueDAO valueDAO;

    private final ArticleDAO articleDAO;

    private final CategoryDtoMapper mapper
            = Mappers.getMapper(CategoryDtoMapper.class);


    public CategoryServiceImpl(CategoryDAO categoryDAO, ValueDAO valueDAO, ArticleDAO articleDAO) {
        this.categoryDAO = categoryDAO;
        this.valueDAO = valueDAO;
        this.articleDAO = articleDAO;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryDAO.findAll().stream()
                .map(mapper::categoryToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return Optional.ofNullable(mapper.categoryToDto(categoryDAO.findCategoryById(id)));
    }

    @Override
    public boolean removeCategoryById(Long id) {
        List<Article> articles  = new ArrayList<>(articleDAO.findArticlesByCategoryId(id));
        List<Value> values = new ArrayList<>(categoryDAO.findCategoryById(id).getValues());
        Category category = categoryDAO.findCategoryById(id);

        for(int i=0; i<values.size();i++){
            category.removeValue(values.get(i));
            for(int j=0; j<articles.size();j++){
                articles.get(j).removeValue(values.get(i));
                articleDAO.updateArticle(articles.get(j));
            }
        }

        categoryDAO.updateCategory(category);


        for(Article article: articles){
            article.removeCategory(category);
            articleDAO.updateArticle(article);
        }
        return categoryDAO.removeCategoryById(id);
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = categoryDAO.saveCategory(mapper.DtoToCategory(categoryDto));
        return mapper.categoryToDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = categoryDAO.updateCategory(mapper.DtoToCategory(categoryDto));
        return mapper.categoryToDto(category);
    }

    @Override
    public void addValue(CategoryValueDto categoryValueDto) {
        valueDAO.addValue(Value.builder().value(categoryValueDto.getValue()).build(),categoryValueDto.getCategoryId());
    }

    @Override
    public void removeValue(Long valueId) {
        valueDAO.removeValueFromCategory(valueId);
    }
}
