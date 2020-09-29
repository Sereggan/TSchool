package org.tsystems.tschool.service;

import org.tsystems.tschool.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List findAll();

    public List findAllByUserId(Long userId);

    public List findAllByOrderId(Long userId);

    public Optional<Order> findById(Long id);

    public void removeOrderById(Long id);

    public void saveOrder(Order order);
}
