package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsystems.tschool.dao.OrderDAO;
import org.tsystems.tschool.entity.Order;

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
        Order test = (Order)orderDAO.getAllOrders().get(0);
        System.out.println("Order status:" + test.getIsPaid());
        return "employee/orders";
    }
}
