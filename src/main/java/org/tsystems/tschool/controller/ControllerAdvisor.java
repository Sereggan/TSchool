package org.tsystems.tschool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.tsystems.tschool.exception.ArticleNotFoundException;
import org.tsystems.tschool.exception.CartNotFoundException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Model model) {
        return "redirect:/";
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public String articleNotFound(Model model) {
        model.addAttribute("message", "Could not find article");
        return "errors/notfound";
    }
}
