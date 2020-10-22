package org.tsystems.tschool.service;

import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderStatusDto;

import java.util.List;

/**
 * The interface Order to access and control Order business logic.
 */
public interface OrderService {

    /**
     * Find all orders.
     *
     * @return the list of orders
     */
    public List<OrderDto> findAll();

    /**
     * Find all orders by username.
     *
     * @param username the username
     * @return the list of orders
     */
    public List<OrderDto> findAllByUsername(String username);

    /**
     * Update status of order.
     *
     * @param orderStatusDto the order status transfer object
     * @param id             the order id
     * @return the order dto
     */
    public OrderDto updateStatus(OrderStatusDto orderStatusDto, Long id);
}
