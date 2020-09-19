package org.tsystems.tschool.service.HibernateJPA;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.dto.ArticleDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.OrderItem;
import org.tsystems.tschool.mapper.ArticleDtoMapper;
import org.tsystems.tschool.service.ArticleService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    final
    ArticleDAO articleDAO;

    private final ArticleDtoMapper mapper
            = Mappers.getMapper(ArticleDtoMapper.class);

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
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
}
