/**
 * 
 */
package com.paypal.bfs.test.employeeserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.employeeserv.domain.EmployeeEntity;



/**
 * @author venkat
 * 
 * Repository for Employee table
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {

}
