package com.paypal.bfs.test.employeeserv.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.paypal.bfs.test.employeeserv.exception.DuplicateEmployeeFoundException;


/**
 * 
 * @author venkat
 * 
 * Implements the logic to render the HTTP response status code, when "DuplicateEmployeeFoundException" exception is thrown
 *
 */
@ControllerAdvice
public class DuplicateEmployeeFoundAdvice {

	  @ResponseBody
	  @ExceptionHandler(DuplicateEmployeeFoundException.class)
	  @ResponseStatus(HttpStatus.CONFLICT)
	  String duplicateEmployeeFoundHandler(DuplicateEmployeeFoundException e) {
	    return e.getMessage();
	  }
	}
