package org.tsystems.tschool.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class NoAccessController {

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
