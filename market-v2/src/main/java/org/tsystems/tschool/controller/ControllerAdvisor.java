package org.tsystems.tschool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tsystems.tschool.exception.ArticleAlreadyExistException;
import org.tsystems.tschool.exception.ItemNotFoundException;


@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String articleNotFound(Model model) {
        model.addAttribute("message", "Could not find this object, Please try again later");
        return "errors/notfound";
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String lockingFailure() {
        return "errors/lockException";
    }

    @ExceptionHandler(ArticleAlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public String createFailure() {
        return "errors/alreadyExists";
    }
}