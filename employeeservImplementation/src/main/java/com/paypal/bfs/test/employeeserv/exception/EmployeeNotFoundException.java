/**
 * 
 */
package com.paypal.bfs.test.employeeserv.exception;


/**
 * @author venkat
 * 
 * Thrown when an Employee entity is not found in the Employee table
 *
 */
public class EmployeeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(Integer id) {
		super("Employee with id:" + id + " not found.");		
	}
}
