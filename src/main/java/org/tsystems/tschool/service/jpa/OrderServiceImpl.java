package org.tsystems.tschool.service.jpa;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderStatusDto;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.mapper.OrderDtoMapper;
import org.tsystems.tschool.service.OrderService;
import org.tsystems.tschool.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    UserService userService;

    private final OrderDtoMapper orderDtoMapper
            = Mappers.getMapper(OrderDtoMapper.class);

    @Override
    public List findAll() {
        List<OrderDto> orderDtos = new ArrayList<>();

        orderDAO.findALl().forEach(order-> orderDtos.add(orderDtoMapper.orderToDto(order)));
        return orderDtos;
    }


    @Override
    public List<OrderDto> findAllByUsername(String username) {
        List<Order> orders
         = orderDAO.findOrdersByUserId(userService.getUserByUsername(username).getId());
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order-> orderDtos.add(orderDtoMapper.orderToDto(order)));
        return orderDtos;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto updateStatus(OrderStatusDto orderStatusDto, Long id) {
        Order order = orderDAO.findById(id);
        order.setIsPaid(orderStatusDto.getIsPaid());
        order.setOrderStatus(orderStatusDto.getOrderStatus());
        return orderDtoMapper.orderToDto(orderDAO.update(order));
    }


    @Override
    public void save(Order order) {

    }
}
