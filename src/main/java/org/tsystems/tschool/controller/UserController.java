package org.tsystems.tschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tsystems.tschool.service.UserService;

import java.security.Principal;

@RequestMapping("/user")
@Controller
public class UserController {

//    @GetMapping("/")
//    public String getUserInfoPage(@PathVariable Long id, Authentication authentication, Model model){
//
//    }
}
