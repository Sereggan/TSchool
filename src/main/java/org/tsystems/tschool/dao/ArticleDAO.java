package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ArticleDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List getAllArticles(){
        return entityManager.createQuery("select e from Article e")
                .getResultList();
    }

    public List getAllArticlesByOrderItem(OrderItem orderItem){
        return entityManager.createQuery("SELECT e from Article e where e.orderItem = ?")
                .setParameter(0, orderItem)
                .getResultList();
    }

    public Article getArticleById(Long id){
        return entityManager.find(Article.class, id);
    }

    @Transactional
    public void removeArticleById(Long id){
        Article articleToRemove = entityManager.find(Article.class, id);
        entityManager.remove(articleToRemove);
    }

    @Transactional
    public void saveArticle(Article article){
        entityManager.persist(article);
    }

    @Transactional
    public void updateArticle(Article article){
        entityManager.merge(article);
    }
}
