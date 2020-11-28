package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Order class data access object
 * Class to access and control order data
 */
@Repository
public class OrderDAO {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Find all orders.
     *
     * @return the list
     */
    public List<Order> findAll() {
        return entityManager.createQuery("select e from Order e", Order.class)
                .getResultList();
    }

    /**
     * Find all order items list.
     *
     * @return the list
     */
    public List<OrderItem> findAllItems() {
        return entityManager.createQuery("select e from Order_item e", OrderItem.class)
                .getResultList();
    }

    /**
     * Find orders by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    public List<Order> findOrdersByUserId(Long userId) {
        return entityManager.createQuery("SELECT e from Order e where e.user.id = ?1", Order.class)
                .setParameter(1, userId)
                .getResultList();
    }

    /**
     * Save order.
     *
     * @param order the order
     * @return the order
     */
    public Order save(Order order) {
        entityManager.persist(order);
        return entityManager.find(Order.class, order.getId());
    }

    /**
     * Find by id order.
     *
     * @param id order id
     * @return the order
     */
    public Order findById(Long id) {
        return entityManager.createQuery("select e from Order e where e.id = ?1", Order.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    /**
     * Update order.
     *
     * @param order the order
     * @return the order
     */
    public Order update(Order order) {
        entityManager.merge(order);
        return entityManager.find(Order.class, order.getId());
    }
}
