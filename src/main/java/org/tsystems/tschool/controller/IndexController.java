package org.tsystems.tschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.tsystems.tschool.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {


    @GetMapping()
    public String getIndexPage(Model model, HttpSession session) {
        return "index";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
