package com.paypal.bfs.test.employeeserv.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;


/**
 * Implementation RestController class for employee resource.
 * Implements the GET employee and POST employee operations.
 * GET Employee operation gets the existing Employee resource by id.
 * POST Employee operation creates a new Employee resource.
 * 
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {	
	
	@Autowired
	private EmployeeService employeeService;

	/**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {        
    	Employee employee = this.employeeService.employeeGetById(Integer.valueOf(id));       	              
       return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Creates the Employee resource
     * 
     * @param employee the employee object. 
     * @return {@link Employee} resource.
     */
	@Override
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
			Employee employeeResponse = this.employeeService.createEmployee(employee);			
				return new ResponseEntity<>(employeeResponse, HttpStatus.OK);			
	}
}
