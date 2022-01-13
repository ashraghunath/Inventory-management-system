package com.ashwin.shopify.controller;

import com.ashwin.shopify.exception.InvalidDataException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Controller class that handles exceptions
 * @author Ashwin Raghunath
 * @since 1.0
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * @param ex represents exception of type NoHandlerFoundException
     * @return error page
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404Exception(NoHandlerFoundException ex) {
        return "error";
    }

    /**
     *
     * @param ex represents exception of type InvalidDataException
     * @param model Model attribute to set error message
     * @return error page displaying invalid data message
     */
    @ExceptionHandler(InvalidDataException.class)
    public String handleInvalidData(InvalidDataException ex, Model model) {
        model.addAttribute("exception",ex.getLocalizedMessage());
        return "error";
    }


}