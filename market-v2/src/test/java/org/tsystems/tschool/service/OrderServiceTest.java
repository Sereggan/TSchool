package org.tsystems.tschool.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.dao.UserDAO;
import org.tsystems.tschool.dto.OrderDto;
import org.tsystems.tschool.dto.OrderStatusDto;
import org.tsystems.tschool.entity.Order;
import org.tsystems.tschool.entity.User;
import org.tsystems.tschool.enums.OrderStatus;
import org.tsystems.tschool.exception.ItemNotFoundException;
import org.tsystems.tschool.service.jpa.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private UserDAO userDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    private List<Order> orders;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        orders.add(Order.builder().id(1L).build());
        orders.add(Order.builder().id(2L).build());
    }

    @Test
    void findAll() {
        when(orderDAO.findAll()).thenReturn(orders);
        List<OrderDto> orderList = orderService.findAll();
        assertEquals(2, orderList.size());
    }

    @Test
    void findAllByUsername() {
        when(orderDAO.findOrdersByUserId(anyLong())).thenReturn(orders);
        when(userDao.getUserByUsername(anyString())).thenReturn(User.builder().id(1L).build());
        List<OrderDto> orderList = orderService.findAllByUsername(anyString());
        assertEquals(2, orderList.size());
    }

    @Test
    void updateStatus() {
        Order order = Order.builder().id(1L).orderStatus(OrderStatus.STATUS_AWAITING_SHIPMENT).isPaid(false).build();
        when(orderDAO.findById(anyLong())).thenReturn(order);
        orderService.updateStatus(OrderStatusDto.builder().isPaid(true).orderStatus(OrderStatus.STATUS_SHIPPED)
                .build(), 1L);
        assertEquals(OrderStatus.STATUS_SHIPPED, order.getOrderStatus());
        assertEquals(true, order.getIsPaid());
    }

    @Test
    void CatchException() {
        when(orderDAO.findById(1L)).thenThrow(EmptyResultDataAccessException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> {
            orderService.updateStatus(any(OrderStatusDto.class),1L);
        });
    }
}