package com.paypal.bfs.test.employeeserv;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.domain.AddressEntity;
import com.paypal.bfs.test.employeeserv.domain.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeResourceImpl.class)

public class EmployeeResourceImplTest {
	
	@Mock
	private ModelMapper modelMapper;

	@MockBean
    private EmployeeService employeeService;
    
    @Mock
	private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
        //EmployeeResourceImpl controller = new EmployeeResourceImpl(employeeService);
        //mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    //@Test
    public void whenCreateEmployee_thenEmployeeCreated() throws Exception {    	
    	EmployeeEntity employeeEntity = new EmployeeEntity(100, "Venkat", "Kondoju", LocalDate.now());
    	AddressEntity addressEntity = new AddressEntity("111 test lane","line2","ellicott city","Maryland","USA","12345");
		Employee employee = new Employee();
		employee.setId(100);
		employee.setFirstName("Venkat");
		employee.setLastName("Kondoju");
		employee.setDateOfBirth("08/05/2020");
		
		Address address = new Address();		
		address.setLine1("111 test lane");
		address.setLine2("line2");
		address.setCity("ellicott city");
		address.setState("Maryland");
		address.setCountry("USA");
		address.setZipCode("12345");
		employee.setAddress(address);
		
		
		Mockito.when(modelMapper.map(address, AddressEntity.class)).thenReturn(addressEntity);
    	Mockito.when(modelMapper.map(employee, EmployeeEntity.class)).thenReturn(employeeEntity);
    	Mockito.when(employeeRepository.existsById(100)).thenReturn(false);    	

        Mockito.when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
        
    }
    
    
    @Test
    public void whenCreateEmployee_thenDuplicateEmployeeFound() throws Exception {    	
    	EmployeeEntity employeeEntity = new EmployeeEntity(100, "Venkat", "Kondoju", LocalDate.now());
    	AddressEntity addressEntity = new AddressEntity("111 test lane","line2","ellicott city","Maryland","USA","12345");
		Employee employee = new Employee();
		employee.setId(100);
		employee.setFirstName("Venkat");
		employee.setLastName("Kondoju");
		employee.setDateOfBirth("08/05/2020");
		
		Address address = new Address();		
		address.setLine1("111 test lane");
		address.setLine2("line2");
		address.setCity("ellicott city");
		address.setState("Maryland");
		address.setCountry("USA");
		address.setZipCode("12345");
		employee.setAddress(address);
		
		
		Mockito.when(modelMapper.map(address, AddressEntity.class)).thenReturn(addressEntity);
    	Mockito.when(modelMapper.map(employee, EmployeeEntity.class)).thenReturn(employeeEntity);
    	Mockito.when(employeeRepository.existsById(100)).thenReturn(true);    	

        Mockito.when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/employees")
                .content(asJsonString(employee))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
