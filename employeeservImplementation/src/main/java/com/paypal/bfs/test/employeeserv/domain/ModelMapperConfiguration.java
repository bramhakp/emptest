package com.paypal.bfs.test.employeeserv.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;


/**
 * 
 * @author venkat
 * 
 * The ModelMapper class maps the Employee JSON to Employee Entity Object and Employee Entity Object to Employee JSON.
 * Also, the ModelMapper maps the Address JSON to Address Entity Object and Address Entity Object to Address JSON.
 * 
 *
 */
@Configuration("EmployeeModelMapperConfiguration")
public class ModelMapperConfiguration {
	
	private static final String PATTERN_DATE = "MM/dd/yyyy";	

	@Bean("EmployeeModelMapper")
    public ModelMapper modelMapper() {
		
		final Converter<String, LocalDate> stringToLocalDateConverter =
				(MappingContext<String, LocalDate> context) -> {				
					String dateString = context.getSource();
					if(dateString == null) {
						return null;
					}				
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);			
					return LocalDate.parse(dateString, formatter);
				};
				
				final Converter<LocalDate, String> localDateToStringConverter =
						(MappingContext<LocalDate, String> context) -> {				
							LocalDate date = context.getSource();
							if(date == null) {
								return null;
							}								
							return date.toString();
						};
		
		
		final Converter<AddressEntity, Address> addressEntityToAddressConverter =
				(MappingContext<AddressEntity, Address> context) -> {				
					AddressEntity addressEntity = context.getSource();
					if(addressEntity == null) {
						return null;
					}				
					Address address = new Address();
					address.setLine1(addressEntity.getLine1());
					address.setLine2(addressEntity.getLine2());
					address.setCity(addressEntity.getCity());
					address.setState(addressEntity.getState());
					address.setCountry(addressEntity.getCountry());
					address.setZipCode(addressEntity.getZipCode());
					return address;
				};
				
				
				
    	
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		modelMapper.addMappings(new PropertyMap<EmployeeEntity, Employee>() {
			@Override
			protected void configure() {
				map().setId(this.source.getId());
				map().setFirstName(this.source.getFirstName());
				map().setLastName(this.source.getLastName());
				using(localDateToStringConverter).map(this.source.getDateOfBirth()).setDateOfBirth(null);	
			    using(addressEntityToAddressConverter).map(this.source.getAddress()).setAddress(null);
			}			
		});
		
		
		modelMapper.addMappings(new PropertyMap<Employee, EmployeeEntity>() {
			@Override
			protected void configure() {				
				map().setFirstName(this.source.getFirstName());
				map().setLastName(this.source.getLastName());
				using(stringToLocalDateConverter).map(this.source.getDateOfBirth()).setDateOfBirth(null);	
				skip().setAddress(null);				
			}			
		});
    	
    	modelMapper.validate();
    	return modelMapper;
    }
}
