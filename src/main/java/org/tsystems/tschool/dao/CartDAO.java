package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Cart;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class CartDAO {

    @PersistenceContext
    EntityManager entityManager;

    public Cart findById(Long id){
        return entityManager.find(Cart.class, id);
    }

    public Cart findByUsername(String username){
        try {
            return entityManager.createQuery("select e from Cart e where e.user.username = ?1", Cart.class)
                    .setParameter(1, username)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public Cart save(Cart cart){
        entityManager.persist(cart);
        return entityManager.createQuery("select e from Cart e where e.user = ?1", Cart.class)
                .setParameter(1,cart.getUser())
                .getSingleResult();
    }
}
