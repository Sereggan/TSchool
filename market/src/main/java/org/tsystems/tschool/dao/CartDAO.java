package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Cart;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Cart class data access object
 * Class to access and control cart data
 */
@Repository
public class CartDAO {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Find by id cart.
     *
     * @param id the cart id
     * @return the cart
     */
    public Cart findById(Long id) {
        return entityManager.createQuery("select e from Cart e where e.id = ?1", Cart.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    /**
     * Find by username cart.
     *
     * @param username the username
     * @return the cart or throw NoResultException if cart doesnt exist
     */
    public Cart findByUsername(String username) {
        return entityManager.createQuery("select e from Cart e where e.user.username = ?1", Cart.class)
                .setParameter(1, username)
                .getSingleResult();
    }

    /**
     * Save cart.
     *
     * @param cart the cart
     * @return the cart
     */
    public Cart save(Cart cart) {
        entityManager.persist(cart);
        return entityManager.createQuery("select e from Cart e where e.user = ?1", Cart.class)
                .setParameter(1, cart.getUser())
                .getSingleResult();
    }

    /**
     * Delete.
     *
     * @param cart the cart
     */
    public void delete(Cart cart) {
        entityManager.remove(cart);
    }

    /**
     * Update cart.
     *
     * @param cart the cart
     * @return the cart
     */
    public Cart update(Cart cart) {
        entityManager.merge(cart);
        return entityManager.find(Cart.class, cart.getId());
    }
}
