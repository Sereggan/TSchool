package org.tsystems.tschool.dao;

import org.springframework.stereotype.Repository;
import org.tsystems.tschool.entity.Article;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Order> findAll(){
        return entityManager.createQuery("select e from Order e" ,Order.class)
                .getResultList();
    }

    public List<OrderItem> findAllItems(){
        return entityManager.createQuery("select e from Order_item e" ,OrderItem.class)
                .getResultList();
    }

    public List<Order>  findOrdersByUserId(Long userId){
        return entityManager.createQuery("SELECT e from Order e where e.user.id = ?1" ,Order.class)
                .setParameter(1, userId)
                .getResultList();
    }

    public Order save(Order order){
        entityManager.persist(order);
        return entityManager.find(Order.class, order.getId());
    }

    public Order findById(Long id){
        return entityManager.createQuery("select e from Order e where e.id = ?1", Order.class)
                .setParameter(1, id)
                .getSingleResult();
    }


    public Order update(Order order){
        entityManager.merge(order);
        return entityManager.find(Order.class, order.getId());
    }
}
