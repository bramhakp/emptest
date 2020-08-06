package com.paypal.bfs.test.employeeserv;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.domain.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;


@RunWith(MockitoJUnitRunner.Silent.class)
public class ServiceTest {
	
	@InjectMocks
	private EmployeeService employeeService; 
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void whenGetOne_thenReturnEmployee() {
		
		EmployeeEntity source = new EmployeeEntity(100, "Venkat", "Kondoju", LocalDate.now());	
		Employee dest = new Employee();
		dest.setId(100);
		dest.setFirstName("Venkat");
		dest.setLastName("Kondoju");
		dest.setDateOfBirth("08/05/2020");
		Mockito.when(modelMapper.map(source, Employee.class)).thenReturn(dest);	
		Mockito.when(employeeRepository.findById(100)).thenReturn(Optional.of(source));
	//	Mockito.when(employeeRepository.findById(100).get()).thenReturn(source);
		//Mockito.when(employeeRepository.existsById(100)).thenReturn(true);		
		
		Employee employeeFound = employeeService.employeeGetById(100);	
		assertThat(employeeFound.getId()).isEqualTo(100);
	}
	
	
	@Test
	public void whenGetOne_thenEmployeeNotFound() {		
		EmployeeEntity source = new EmployeeEntity(100, "Venkat", "Kondoju", LocalDate.now());	
		Employee dest = new Employee();
		dest.setId(100);
		dest.setFirstName("Venkat");
		dest.setLastName("Kondoju");
		dest.setDateOfBirth("08/05/2020");
		Mockito.when(modelMapper.map(source, Employee.class)).thenReturn(dest);	
		Mockito.when(employeeRepository.findById(100)).thenReturn(Optional.of(source));
		//Mockito.when(employeeRepository.findById(100).get()).thenReturn(source);
		//Mockito.when(employeeRepository.existsById(100)).thenReturn(true);		
		
		Employee employeeFound = employeeService.employeeGetById(100);	
		assertThat(employeeFound.getId()).isEqualTo(100);
	}
}
