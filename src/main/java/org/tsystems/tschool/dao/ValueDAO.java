package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
