package com.tech2java.eis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tech2java.eis.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

	
	//Derived Query method
	
	Employee findEmployeeByEmpEmail(String email);
}
