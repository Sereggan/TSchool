package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CategoryDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Category> findAll(){
        return entityManager.createQuery("select e from Category e", Category.class)
                .getResultList();
    }


    public Category findById(Long id){
        return entityManager.find(Category.class, id);
    }

    public Category save(Category category){
        entityManager.persist(category);
        return entityManager.find(Category.class, category.getId());
    }

    public Category update(Category category){
        entityManager.merge(category);
        return entityManager.find(Category.class, category.getId());
    }

    public boolean remove(Category category){
        entityManager.remove(category);
        return entityManager.find(Category.class, category.getId())==null;
    }
}
