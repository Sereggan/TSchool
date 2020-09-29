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
        UserDto userDto = userService.getUserByUsername(authentication.getName());
        if (userDto==null || !Objects.equals(authentication.getName(), userDto.getUsername())){
            return "redirect:";
        }else {
            model.addAttribute("user",userDto);
            return "user/user-info-page";
        }
    }

    @PostMapping("/update/{username}")
    public String updateUser(@PathVariable("username") String username,Authentication authentication, @ModelAttribute("user") UserDto userDto) {

            userService.updateUser(userDto);

        return "redirect:/user";
    }

    @PostMapping("/update/password/{username}")
    public String updateUserPassword(@PathVariable("username") String username,Authentication authentication, @RequestParam String password) {


        return "redirect:/user";
    }
}
