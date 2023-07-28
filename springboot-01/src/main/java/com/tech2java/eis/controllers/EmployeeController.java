package com.tech2java.eis.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tech2java.eis.controllers.dto.Employee;

@Controller
@RequestMapping("/api/employees")
public class EmployeeController {

	@GetMapping
	@ResponseBody
	public List<Employee> getEmployees() {
		return Arrays.asList(new Employee(10,"Ramesh",34),
							 new Employee(20,"Suresh",35));
		
	}
	
	/*
	@GetMapping("/{empId}")
	//@GetMapping("/getEmployeeById/{empId}")
	@ResponseBody
	public Employee getEmployeeById(@PathVariable("empId") String empId) {
		return new Employee(Integer.valueOf(empId),"Ramesh",34);
	}
	*/
	
	@GetMapping("/getEmployeeByName")
	@ResponseBody
	public Employee getEmployeeByName(@RequestParam("empName") String empName) {
		return new Employee(1,empName,34);
	}
	
	@GetMapping("/getEmployee")
	@ResponseBody
	public Employee getEmployee(@RequestBody Employee employee) {
		
		return employee;
	}
	
	
}
