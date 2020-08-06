/**
 * 
 */
package com.paypal.bfs.test.employeeserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.employeeserv.domain.AddressEntity;

/**
 * @author venkat
 * 
 * Repository for Address table
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer>, JpaSpecificationExecutor<AddressEntity> {

}
