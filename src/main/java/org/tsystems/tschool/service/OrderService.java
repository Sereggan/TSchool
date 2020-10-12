package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderStatusDto;
import org.tsystems.tschool.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public List<OrderDto> findAll();

    public List<OrderDto> findAllByUsername(String username);

    public OrderDto update(OrderDto orderDto);

    public OrderDto updateStatus(OrderStatusDto orderStatusDto, Long id);
}
