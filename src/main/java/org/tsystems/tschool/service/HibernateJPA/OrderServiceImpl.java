package org.tsystems.tschool.service.HibernateJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Override
    public List findAll() {
        return orderDAO.getAllOrders();
    }

    @Override
    public List findAllByUserId(Long userId) {
        return orderDAO.findOrdersByUserId(userId);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void removeOrderById(Long id) {

    }

    @Override
    public void saveOrder(Order order) {

    }
}
