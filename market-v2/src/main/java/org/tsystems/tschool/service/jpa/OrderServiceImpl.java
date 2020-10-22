package org.tsystems.tschool.service.jpa;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderStatusDto;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.exception.ItemNotFoundException;
import org.tsystems.tschool.mapper.OrderDtoMapper;
import org.tsystems.tschool.service.OrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    final
    OrderDAO orderDAO;

    final
    UserDAO userDAO;

    private final OrderDtoMapper orderDtoMapper
            = Mappers.getMapper(OrderDtoMapper.class);

    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderDAO orderDAO, UserDAO userDAO) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<OrderDto> findAll() {
        List<OrderDto> orderDtos = new ArrayList<>();

        orderDAO.findAll().forEach(order -> orderDtos.add(orderDtoMapper.orderToDto(order)));
        return orderDtos;
    }

    @Override
    public List<OrderDto> findAllByUsername(String username) {
        List<Order> orders
                = orderDAO.findOrdersByUserId(userDAO.getUserByUsername(username).getId());
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> orderDtos.add(orderDtoMapper.orderToDto(order)));
        return orderDtos;
    }

    @Override
    public OrderDto updateStatus(OrderStatusDto orderStatusDto, Long id) {
        Order order;
        try {
            order = orderDAO.findById(id);
        } catch (EmptyResultDataAccessException e) {
            log.info("Could not find order with such id: " + id);
            throw new ItemNotFoundException("Order doesnt exist");
        }
        order.setIsPaid(orderStatusDto.getIsPaid());
        order.setOrderStatus(orderStatusDto.getOrderStatus());
        return orderDtoMapper.orderToDto(order);
    }
}
