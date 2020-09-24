package org.tsystems.tschool.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Category;
import org.tsystems.tschool.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ValueDAO valueDAO;

    public List<Category> getAllCategories(){
        return entityManager.createQuery("select e from Category e", Category.class)
                .getResultList();
    }

    public List<Category> findAllByArticleId(Long id){
        return entityManager.createQuery("select e.categories from Article e where e.id = ?1", Category.class)
                .setParameter(1, id)
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
