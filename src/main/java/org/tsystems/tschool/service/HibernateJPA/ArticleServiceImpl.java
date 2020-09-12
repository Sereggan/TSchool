package org.tsystems.tschool.service.HibernateJPA;

import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.ArticleDAO;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.OrderItem;
import org.tsystems.tschool.service.ArticleService;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    final
    ArticleDAO articleDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List findAll() {
        return articleDAO.getAllArticles();
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
