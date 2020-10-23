package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.ArticleRating;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

/**
 * Article class data access object
 * Class to access and control article data
 */
@Repository
public class ArticleDAO {

    private static final String SELECT_ARTICLE_BY_ID = "select e from Article e where e.id = ?1";
    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Find all articles
     *
     * @return the list of articles
     */
    public List<Article> findAll() {
        return entityManager.createQuery("select e from Article e", Article.class)
                .getResultList();
    }

    /**
     * Find by id article.
     *
     * @param id the id
     * @return the article
     */
    public Article findById(Long id) {
        return entityManager.createQuery(SELECT_ARTICLE_BY_ID, Article.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    /**
     * Find by id with lock article.
     *
     * @param id the id
     * @return the article
     */
    public Article findByIdWithLock(Long id) {
        Article article = entityManager.createQuery(SELECT_ARTICLE_BY_ID, Article.class)
                .setParameter(1, id)
                .getSingleResult();
        entityManager.lock(article, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
        return article;
    }

    /**
     * Remove by id
     *
     * @param id the id
     * @return the boolean if success
     */
    public boolean removeById(Long id) {
        Article article = entityManager.createQuery(SELECT_ARTICLE_BY_ID, Article.class)
                .setParameter(1, id)
                .getSingleResult();
        entityManager.remove(article);
        return entityManager.find(Article.class, id) == null;
    }

    /**
     * Find by articles by category
     *
     * @param id the category id
     * @return the set of articles
     */
    public Set<Article> findByCategoryId(Long id) {
        return entityManager.find(Category.class, id).getArticleSet();
    }

    /**
     * Save article.
     *
     * @param article the article
     * @return the article
     */
    public Article save(Article article) throws NonUniqueResultException {
        entityManager.persist(article);
        return entityManager.createQuery("select e from Article e where e.title = ?1", Article.class)
                .setParameter(1, article.getTitle())
                .getSingleResult();
    }

    /**
     * Update article.
     *
     * @param article the article
     * @return the article
     */
    public Article update(Article article) {
        entityManager.merge(article);
        return entityManager.find(Article.class, article.getId());
    }

    /**
     * Find best sellers articles
     *
     * @param limit the limit
     * @return the list representing rating of articles
     */
    public List<ArticleRating> findBestSellers(Long limit) {
        return entityManager.createNativeQuery("SELECT id, order_article_title as article, " +
                "sum(order_item_price * order_item_quantity) as price FROM order_item group by article " +
                "order by price desc limit ?1", ArticleRating.class)
                .setParameter(1, limit)
                .getResultList();
    }
}
