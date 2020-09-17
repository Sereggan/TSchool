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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    final
    ArticleDAO articleDAO;

    private ArticleDtoMapper mapper
            = Mappers.getMapper(ArticleDtoMapper.class);

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List findAll() {

        return articleDAO.getAllArticles().stream()
                .map(article-> mapper.articleToDto((Article)article))
                .collect(Collectors.toList());
    }

    @Override
    public List findAllByOrderItem(OrderItem orderItem) {
        return articleDAO.getAllArticlesByOrderItem(orderItem);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(articleDAO.getArticleById(id));
    }

    @Override
    public void removeArticleById(Long id) {
        articleDAO.removeArticleById(id);
    }

    @Override
    public void saveArticle(Article article) {
        articleDAO.saveArticle(article);
    }
}
