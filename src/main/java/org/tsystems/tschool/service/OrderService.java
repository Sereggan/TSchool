package org.tsystems.tschool.service;

import org.tsystems.tschool.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List findAll();

    public List findAllByUsername(String username);

    public Optional<Order> findById(Long id);

    public void remove(Long id);

    public void save(Order order);
}
