package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class ArticleDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Article> findALl(){
        return entityManager.createQuery("select e from Article e", Article.class)
                .getResultList();
    }

    public Article findArticleById(Long id){
        return entityManager.find(Article.class, id);
    }

    public boolean removeArticleById(Long id){
        Article articleToRemove = entityManager.find(Article.class, id);
        entityManager.remove(articleToRemove);
        return entityManager.find(Article.class, id) == null;
    }

    public Set<Article> findArticlesByCategoryId(Long id){
        return entityManager.find(Category.class, id).getArticleSet();
    }

    public Article saveArticle(Article article){
        entityManager.persist(article);
        return entityManager.find(Article.class, article);
    }

    public Article updateArticle(Article article){
        entityManager.merge(article);
        return entityManager.find(Article.class, article);
    }
}
