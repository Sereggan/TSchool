package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Category> getAllCategories(){
        return entityManager.createQuery("select e from Category e", Category.class)
                .getResultList();
    }

    public Category getCategoryById(Long id){
        return entityManager.find(Category.class, id);
    }


    public void removeCategoryById(Long id){
        Category category = entityManager.find(Category.class, id);
        entityManager.remove(category);
    }

    public void saveCategory(Category category){
        entityManager.persist(category);
    }

    public void updateCategory(Category category){
        entityManager.merge(category);
    }

}
