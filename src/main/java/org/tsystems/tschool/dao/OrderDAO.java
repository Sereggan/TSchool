package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Order> getAllOrders(){
        return entityManager.createQuery("select e from Order e" ,Order.class)
                .getResultList();
    }

    public List<Order>  findOrdersByUserId(Long userId){
        return entityManager.createQuery("SELECT e from Order e where e.user.id = ?1" ,Order.class)
                .setParameter(1, userId)
                .getResultList();
    }

    public List<Order>  findOrderItemsByOrderId(Long orderId){
        return entityManager.createQuery("SELECT e from Order_item e where e.order.id = ?1" ,Order.class)
                .setParameter(1, orderId)
                .getResultList();
    }
}
