package org.tsystems.tschool.service.HibernateJPA;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.CategoryDAO;
import org.tsystems.tschool.dto.CategoryDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.mapper.CategoryDtoMapper;
import org.tsystems.tschool.service.CategoryService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    private final CategoryDtoMapper mapper
            = Mappers.getMapper(CategoryDtoMapper.class);


    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryDAO.getAllCategories().stream()
                .map(mapper::categoryToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List findAllByOrderItem(CategoryDto orderItem) {
        return null;
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void removeCategoryById(Long id) {
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        categoryDAO.saveCategory(mapper.DtoToCategory(categoryDto));
    }
}
