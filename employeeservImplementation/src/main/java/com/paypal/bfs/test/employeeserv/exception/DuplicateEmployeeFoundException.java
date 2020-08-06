/**
 * 
 */
package com.paypal.bfs.test.employeeserv.exception;

/**
 * @author venkat
 * 
 * Thrown when creating an existing Employee entity in the Employee table
 *
 */
public class DuplicateEmployeeFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateEmployeeFoundException(Integer id) {
		super("Employee with id:" + id + " Found. Cannot Create Duplicate Employee.");		
	}
}
