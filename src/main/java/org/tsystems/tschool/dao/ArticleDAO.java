package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class ArticleDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Article> findAll(){
        return entityManager.createQuery("select e from Article e", Article.class)
                .getResultList();
    }

    public Article findById(Long id){
        return entityManager.find(Article.class, id);
    }

    public Article findByIdWithLock(Long id){
        Article article = entityManager.find(Article.class, id);
        entityManager.lock(article, LockModeType.PESSIMISTIC_READ);
        return article;
    }

    public boolean removeById(Long id){
        Article articleToRemove = entityManager.find(Article.class, id);
        entityManager.remove(articleToRemove);
        return entityManager.find(Article.class, id) == null;
    }

    public Set<Article> findByCategoryId(Long id){
        return entityManager.find(Category.class, id).getArticleSet();
    }

    public Article save(Article article){
        entityManager.persist(article);
        return entityManager.createQuery("select e from Article e where e.title = ?1", Article.class)
                .setParameter(1,article.getTitle())
                .getSingleResult();
    }

    public Article update(Article article){
        entityManager.merge(article);
        return entityManager.find(Article.class, article.getId());
    }

}
