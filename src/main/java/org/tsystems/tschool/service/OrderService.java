package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderStatusDto;

import java.util.List;

public interface OrderService {

    public List<OrderDto> findAll();

    public List<OrderDto> findAllByUsername(String username);

    public OrderDto updateStatus(OrderStatusDto orderStatusDto, Long id);
}
