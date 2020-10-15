package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Value class data access object
 * Class to access and control value data
 */
@Repository
public class ValueDAO {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Add value value.
     *
     * @param value      the value
     * @param categoryId the category id
     * @return the value
     */
    public Value addValue(Value value, Long categoryId) {
        Category category = entityManager.find(Category.class, categoryId);
        value.setCategory(category);
        try {
            return entityManager.createQuery("select e from Value e where e.value = ?1 and e.category = ?2", Value.class)
                    .setParameter(1, value.getValue())
                    .setParameter(2, value.getCategory())
                    .getSingleResult();
        } catch (NoResultException e) {
            entityManager.persist(value);
            return entityManager.createQuery("select e from Value e where e.value = ?1 and e.category = ?2", Value.class)
                    .setParameter(1, value.getValue())
                    .setParameter(2, value.getCategory())
                    .getSingleResult();
        }
    }

    /**
     * Remove value from category.
     *
     * @param id the id
     */
    public void removeValueFromCategory(Long id) {
        Value value = entityManager.find(Value.class, id);
        Category category = value.getCategory();
        category.removeValue(value);
        Set<Article> articles = new HashSet<>(value.getArticles());

        if (!articles.isEmpty()) {
            for (Article article : articles) {
                article.removeValue(value);
            }
        }
        category.setArticleSet(articles);
        entityManager.merge(category);
        entityManager.remove(value);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Value> findAll() {
        return entityManager.createQuery("select e from Value e", Value.class)
                .getResultList();
    }

    /**
     * Find by id value.
     *
     * @param id the id
     * @return the value
     */
    public Value findById(Long id) {
        return entityManager.find(Value.class, id);
    }

    /**
     * Remove.
     *
     * @param value the value
     */
    public void remove(Value value) {
        entityManager.remove(value);
    }
}
