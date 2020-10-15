package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Category class data access object
 * Class to access and control category data
 */
@Repository
public class CategoryDAO {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Find all categories list.
     *
     * @return the list
     */
    public List<Category> findAll(){
        return entityManager.createQuery("select e from Category e", Category.class)
                .getResultList();
    }

    /**
     * Find by id category.
     *
     * @param id the id
     * @return the category
     */
    public Category findById(Long id){
        return entityManager.find(Category.class, id);
    }

    /**
     * Save category.
     *
     * @param category the category
     * @return the category
     */
    public Category save(Category category){
        entityManager.persist(category);
        return entityManager.find(Category.class, category.getId());
    }

    /**
     * Update category.
     *
     * @param category the category
     * @return the category
     */
    public Category update(Category category){
        entityManager.merge(category);
        return entityManager.find(Category.class, category.getId());
    }

    /**
     * Remove boolean.
     *
     * @param category the category
     * @return the boolean if success
     */
    public boolean remove(Category category){
        entityManager.remove(category);
        return entityManager.find(Category.class, category.getId())==null;
    }
}
