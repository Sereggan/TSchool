package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Cart;
import org.tsystems.tschool.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CartDAO {

    @PersistenceContext
    EntityManager entityManager;

    public Cart findById(Long id){
        return entityManager.find(Cart.class, id);
    }

}
