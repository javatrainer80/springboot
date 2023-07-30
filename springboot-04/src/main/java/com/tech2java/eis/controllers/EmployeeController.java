package com.tech2java.eis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech2java.eis.entity.Employee;
import com.tech2java.eis.repository.EmployeeRepository;

@RestController
@RequestMapping(path = "/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	// static sorting
	@GetMapping
	public List<Employee> getEmployees() {

		// List<Employee> list=employeeRepository.findByOrderByEmpNameDesc();
		List<Employee> list = employeeRepository.findByOrderByEmpName();
		return list;

	}

	//Dynamic sorting
	@GetMapping("/desc")
	public List<Employee> getEmployeesDynamicSorting() {
        //List<Employee> list = employeeRepository.findAll(Sort.by("empName").descending());
		List<Employee> list = employeeRepository.findAll(Sort.by("empName").ascending());
		return list;

	}

}
