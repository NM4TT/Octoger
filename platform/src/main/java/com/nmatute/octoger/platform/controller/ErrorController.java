package com.nmatute.octoger.platform.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    
    @ExceptionHandler(Exception.class)
    public String error(){
        return "error";
    }

}
