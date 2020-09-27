package org.tsystems.tschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tsystems.tschool.dto.UserDto;
import org.tsystems.tschool.service.UserService;

import java.util.Objects;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String getUserInfoPage( Authentication authentication, Model model) {
        if(authentication==null) return "redirect:";
        UserDto userDto = userService.getUserByUsername(authentication.getName());
        if (userDto==null || !Objects.equals(authentication.getName(), userDto.getUsername())){
            return "redirect:";
        }else {
            userDto = userService.updateUser(userDto);
            model.addAttribute("user",userDto);
            return "user/user-info-page";
        }
    }

    @PostMapping("/user/{username}")
    public String updateUser(@PathVariable("username") String username,Authentication authentication, @ModelAttribute("user") UserDto userDto) {
        if(Objects.equals(username, authentication.getName())) {
            userService.updateUser(userDto);
            return "redirect:/user";
        }else
            return "redirect:/user";
    }
}
