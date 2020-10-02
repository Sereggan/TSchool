package org.tsystems.tschool.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/add/{id}")
    public String addArticle(@PathVariable Long id,  Authentication authentication, HttpSession session){
        return null;
    }
}
