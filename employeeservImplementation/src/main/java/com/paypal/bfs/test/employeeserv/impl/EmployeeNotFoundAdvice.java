package com.paypal.bfs.test.employeeserv.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;

/**
 * 
 * @author venkat
 * 
 * Implements the logic to render the HTTP response status code, when "EmployeeNotFoundException" exception is thrown
 *
 */
@ControllerAdvice
public class EmployeeNotFoundAdvice {

	  @ResponseBody
	  @ExceptionHandler(EmployeeNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String employeeNotFoundHandler(EmployeeNotFoundException e) {
	    return e.getMessage();
	  }
	}
