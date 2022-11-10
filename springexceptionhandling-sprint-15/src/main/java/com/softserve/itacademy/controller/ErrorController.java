package com.softserve.itacademy.controller;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView entityNotFound(EntityNotFoundException exception){
        return handle(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView internalError(Exception exception){
        return handle(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullEntityReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView nullEntityReference(Exception exception){
        return handle(exception, HttpStatus.BAD_REQUEST);
    }


    private ModelAndView handle(Exception exception, HttpStatus status){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("code", status.value());
        modelAndView.addObject("error", exception.getMessage());
        return modelAndView;
    }

}
