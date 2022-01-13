package com.ashwin.shopify.controller;

import com.ashwin.shopify.exception.InvalidDataException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404Exception(NoHandlerFoundException ex) {
        return "error";
    }

    @ExceptionHandler(InvalidDataException.class)
    public String handleInvalidData(InvalidDataException ex, Model model) {
        model.addAttribute("exception",ex.getLocalizedMessage());
        return "error";
    }


}