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

    public List<Article> getAllArticles(){
        return entityManager.createQuery("select e from Article e", Article.class)
                .getResultList();
    }

    public List<Article> getAllArticlesByOrderItem(OrderItem orderItem){
        return entityManager.createQuery("SELECT e from Article e where e.orderItem = ?1", Article.class)
                .setParameter(1, orderItem)
                .getResultList();
    }

    public Article getArticleById(Long id){
        return entityManager.find(Article.class, id);
    }

    public void removeArticleById(Long id){
        Article articleToRemove = entityManager.find(Article.class, id);
        entityManager.remove(articleToRemove);
    }

    public Set<Article> getArticlesByCategoryId(Long id){
        return entityManager.find(Category.class, id).getArticleSet();
    }


    public void saveArticle(Article article){
        entityManager.persist(article);
    }

    public void updateArticle(Article article){
        entityManager.merge(article);
    }
}
