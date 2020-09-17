package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.entity.Order;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    final
    OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping
    public String getAllOrders(Model model){
        model.addAttribute("orders", orderDAO.getAllOrders());
        return "orders/orders";
    }

    @GetMapping("/{id}")
    public String getOrderPage(@PathVariable Long id){
        List orders = orderDAO.findOrderItemsByOrderId(id);
        return "orders/order-info-page";
    }
}
