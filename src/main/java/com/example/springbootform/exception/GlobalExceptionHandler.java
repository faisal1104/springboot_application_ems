package com.example.springbootform.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleException(ResourceNotFoundException e,
                                HttpServletRequest request,
                                HttpServletResponse response, Model m){
        m.addAttribute("errorMsg",e.getMessage());
        return "errors/error404";
    }


}
