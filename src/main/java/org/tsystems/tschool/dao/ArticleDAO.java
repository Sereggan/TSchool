package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.ArticleRatingDto;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class ArticleDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Article> findAll() {
        return entityManager.createQuery("select e from Article e", Article.class)
                .getResultList();
    }

    public Article findById(Long id) {
        return entityManager.createQuery("select e from Article e where e.id = ?1", Article.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    public Article findByIdWithLock(Long id) {
        Article article = entityManager.createQuery("select e from Article e where e.id = ?1", Article.class)
                .setParameter(1, id)
                .getSingleResult();
        entityManager.lock(article, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
        return article;
    }

    public boolean removeById(Long id) {
        Article articleToRemove = entityManager.find(Article.class, id);
        entityManager.remove(articleToRemove);
        return entityManager.find(Article.class, id) == null;
    }

    public Set<Article> findByCategoryId(Long id) {
        return entityManager.find(Category.class, id).getArticleSet();
    }

    public Article save(Article article) {
        entityManager.persist(article);
        return entityManager.createQuery("select e from Article e where e.title = ?1", Article.class)
                .setParameter(1, article.getTitle())
                .getSingleResult();
    }

    public Article update(Article article) {
        entityManager.merge(article);
        return entityManager.find(Article.class, article.getId());
    }

    public List<ArticleRatingDto> findBestSellers(Long limit) {
        return entityManager.createNativeQuery("SELECT id, order_article_title as article, " +
                "sum(order_item_price * order_item_quantity) as price FROM order_item group by article " +
                "order by price desc limit ?1", ArticleRatingDto.class)
                .setParameter(1, limit)
                .getResultList();
    }
}
