package org.tsystems.tschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.OrderStatusDto;
import org.tsystems.tschool.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping()
    public String getAllOrdersPage(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders/orders";
    }

    @PostMapping("/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute("order") OrderStatusDto orderStatusDto,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "orders/orders";
        }
        orderService.updateStatus(orderStatusDto, id);
        return "redirect:/orders";
    }
}
