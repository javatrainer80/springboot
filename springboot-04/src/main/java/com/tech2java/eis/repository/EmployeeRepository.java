package com.tech2java.eis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech2java.eis.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	
	//Derived Query method for Sorting Descending
	List<Employee> findByOrderByEmpNameDesc();
	
	//Derived Query method for Sorting Ascending
	List<Employee> findByOrderByEmpName();
		
}
