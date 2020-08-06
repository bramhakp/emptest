/**
 * 
 */
package com.paypal.bfs.test.employeeserv.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.domain.AddressEntity;
import com.paypal.bfs.test.employeeserv.domain.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.DuplicateEmployeeFoundException;
import com.paypal.bfs.test.employeeserv.exception.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.repository.AddressRepository;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;

/**
 * @author venkat
 * 
 * Implements the getEmployee and createEmployee operations with the repository invocations.
 *
 */
@Service
public class EmployeeService {
	
	@Autowired
	@Qualifier("EmployeeModelMapper")
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AddressRepository addressRepository;	
	
	/**
	 * Find the Employee resource by id
	 * 
	 * @param id the employee id
	 * @return the Employee resource if found, otherwise throw the 'EmployeeNotFoundException" exception
	 */
	public Employee employeeGetById(Integer id) {		
			EmployeeEntity employeeEntity = 
					this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
			return this.modelMapper.map(employeeEntity, Employee.class);						
	}
	
	
	/**
	 * Create the Employee resource. If the Employee Resource already exists, throw the "DuplicateEmployeeFoundException" exception
	 * 
	 * @param employee the Employee resource to be created
	 * @return the new created Employee resource
	 */
	@Transactional
	public Employee createEmployee(Employee employee) {		
		EmployeeEntity employeeEntity = this.modelMapper.map(employee, EmployeeEntity.class);		
		if(employeeEntity != null && !this.employeeRepository.existsById(employeeEntity.getId())) {
			EmployeeEntity createdEmployeeEntity = this.employeeRepository.saveAndFlush(employeeEntity);
			Address address = employee.getAddress();
			AddressEntity addressEntity = this.modelMapper.map(address, AddressEntity.class);
			addressEntity.setEmployee(createdEmployeeEntity);
			this.addressRepository.saveAndFlush(addressEntity);
			createdEmployeeEntity.setAddress(addressEntity);
			return this.modelMapper.map(createdEmployeeEntity, Employee.class);
		}		
		else {
			throw new DuplicateEmployeeFoundException(employee.getId());
		}		
	}
}
