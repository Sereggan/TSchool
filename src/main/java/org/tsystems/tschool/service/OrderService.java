package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderStatusDto;
import org.tsystems.tschool.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List findAll();

    public List findAllByUsername(String username);

    public Optional<Order> findById(Long id);

    public void remove(Long id);

    public OrderDto update(OrderDto orderDto);

    public OrderDto updateStatus(OrderStatusDto orderStatusDto, Long id);

    public void save(Order order);
}
