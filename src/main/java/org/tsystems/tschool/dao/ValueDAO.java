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
import java.util.Optional;
import java.util.Set;

@Repository
public class ValueDAO {

    @PersistenceContext
    EntityManager entityManager;

    public Value addValue(Value value, Long categoryId){
        Category category = entityManager.find(Category.class, categoryId);
        value.setCategory(category);
        try {
            return entityManager.createQuery("select e from Value e where e.value = ?1 and e.category = ?2", Value.class)
                    .setParameter(1, value.getValue())
                    .setParameter(2, value.getCategory())
                    .getSingleResult();
        }catch (NoResultException e){
            entityManager.persist(value);
            return entityManager.createQuery("select e from Value e where e.value = ?1 and e.category = ?2", Value.class)
                    .setParameter(1,value.getValue())
                    .setParameter(2,value.getCategory())
                    .getSingleResult();
        }
    }

    public void removeValueFromCategory(Long id){
        Value value = entityManager.find(Value.class, id);
        Category category = value.getCategory();
        category.removeValue(value);
        Set<Article> articles = new HashSet<>(value.getArticles());

        if(!articles.isEmpty()){
            for(Article article:articles){
                article.removeValue(value);
            }
        }
        category.setArticleSet(articles);
        entityManager.merge(category);
        entityManager.remove(value);
    }

    public List<Value> findAll(){
        return entityManager.createQuery("select e from Value e", Value.class)
                .getResultList();
    }

    public Value findById(Long id){
        return entityManager.find(Value.class, id);
    }

    public void removeValue(Value value){
        entityManager.remove(value);
    }
}
