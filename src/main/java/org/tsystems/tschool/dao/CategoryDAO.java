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

    public List<Category> findAllByArticleId(Long id){
        return entityManager.createQuery("select e.categories from Article e where e.id = ?1", Category.class)
                .setParameter(1, id)
                .getResultList();
    }

    public Category findCategoryById(Long id){
        return entityManager.find(Category.class, id);
    }

    public Category saveCategory(Category category){
        entityManager.persist(category);
        return entityManager.find(Category.class, category.getId());
    }

    public Category updateCategory(Category category){
        entityManager.merge(category);
        return entityManager.find(Category.class, category.getId());
    }

    public boolean removeCategory(Category category){
        entityManager.remove(category);
        return entityManager.find(Category.class, category.getId())==null;
    }
}
