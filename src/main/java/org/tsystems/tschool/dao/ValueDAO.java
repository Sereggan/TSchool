package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ValueDAO {

    @PersistenceContext
    EntityManager entityManager;

    public void addValue(Value value, Long categoryId){
        Category category = entityManager.find(Category.class, categoryId);
        value.setCategory(category);
        entityManager.persist(value);
    }

    public void removeValueFromCategory(Long id){
        Value value = entityManager.find(Value.class, id);
        Category category = value.getCategory();
        category.removeValue(value);
        entityManager.merge(category);
    }


    public List<Value> findAll(){
        return entityManager.createQuery("select e from Value e", Value.class)
                .getResultList();
    }

    public Value findById(Long id){
        return entityManager.find(Value.class, id);
    }
}
