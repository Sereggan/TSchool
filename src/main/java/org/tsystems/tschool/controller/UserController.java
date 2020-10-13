package org.tsystems.tschool.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.service.OrderService;
import org.tsystems.tschool.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Objects;

@RequestMapping("/user")
@Controller
@Validated
public class UserController {

    final UserService userService;

    final OrderService orderService;

    public UserController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping()
    public String getUserInfoPage(Authentication authentication, Model model) {
        UserDto userDto = userService.getUserByUsername(authentication.getName());
        if (userDto == null || !Objects.equals(authentication.getName(), userDto.getUsername())) {
            return "redirect:";
        } else {
            model.addAttribute("user", userDto);
            return "user/user-info-page";
        }
    }

    @PostMapping("/update/{username}")
    public String updateUser(@PathVariable("username") String username, Authentication authentication,
                             @Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "user/user-info-page";
        }
        userDto.setUsername(authentication.getName());
        userService.updateUser(userDto);
        return "redirect:/user";
    }

    @PostMapping("/update/password")
    public String updateUserPassword(@RequestParam("password") @NotBlank @Size(min = 4, max = 20,
            message = "password's length should be between 4 and 20") String password, Authentication authentication) {
        userService.updatePassword(authentication.getName(), password);
        return "redirect:/user";
    }

    @GetMapping("/orders")
    public String getOrdersPage(Authentication authentication, Model model) {
        model.addAttribute("orders", orderService.findAllByUsername(authentication.getName()));
        return "user/orders";
    }
}
