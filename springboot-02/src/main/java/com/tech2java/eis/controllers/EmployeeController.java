package com.tech2java.eis.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tech2java.eis.controllers.dto.Employee;
import com.tech2java.eis.domain.Response;

@RestController
@RequestMapping(path="/api/employees",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public class EmployeeController {

	//http://localhost:8080/api/employees
	@GetMapping
	public List<Employee> getEmployees() {
		
		//checking Global exception handler is working or not.
		
		/*
		 * String s=null;
		 * s.concat("hello");
		 */
		
		return Arrays.asList(new Employee(10,"Ramesh",34),
							 new Employee(20,"Suresh",35));
		
	}
	
	//http://localhost:8080/api/employees/getEmployeeByName?empName=Ramesh
	@GetMapping("/getEmployeeByName")
	public Employee getEmployeeByName(@RequestParam("empName") String empName) {
		return new Employee(1,empName,34);
	}
	
	@GetMapping("/getEmployee")
	@ResponseBody
	public Employee getEmployee(@RequestBody Employee employee) {
		
		return employee;
	}
	
	@PostMapping("/saveEmp")
	public ResponseEntity<Response> saveEmployee(@RequestHeader("client") String client,
												 @RequestBody Employee employee) {
		//@Valid- need to look
		System.out.println("Request is comming from client::"+client);
		
		System.out.println("Employee::"+employee);
		//need to write logic to save object into database.
		System.out.println("Employee obejct saved successfully.");
		
		Response response = new Response();
		response.setStatusCode("200");
		response.setStatusMessage("Employee Object Saved Successfully.");

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.header("saved", "true")
				.body(response);
	}
	
	@DeleteMapping("/deleteEmp")
	public ResponseEntity<Response> deleteEmployee(RequestEntity<Employee> requestEntity) {
		
		
		HttpHeaders httpHeaders=requestEntity.getHeaders();
		httpHeaders.forEach((key,value)->{
			System.out.println("KEY="+key +"::VALUE=>"+value.stream().collect(Collectors.joining("|")));
		});
		
		Employee employee=requestEntity.getBody();
		System.out.println("Employee ID::"+employee.getEmpId());
		//need to write logic to delete object into database. //deleteById
		System.out.println("Employee obejct deleted successfully."+employee);
		
		Response response = new Response();
		response.setStatusCode("200");
		response.setStatusMessage("Employee Object Deleted Successfully.");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(response);
	}
	
	@PatchMapping("/updateAge")
	public ResponseEntity<Response> patchEmployee(RequestEntity<Employee> requestEntity) {
		
		
		HttpHeaders httpHeaders=requestEntity.getHeaders();
		httpHeaders.forEach((key,value)->{
			System.out.println("KEY="+key +"::VALUE=>"+value.stream().collect(Collectors.joining("|")));
		});
		
		Employee employee=requestEntity.getBody();
		System.out.println("Employee ID::"+employee.getEmpId());
		
		//Get Employee by ID
		//then check if employee available if yes,
		// then update the age else,
		//send error message with BAD request and Employee not found status message.
		
		//need to write logic to delete object into database. //deleteById
		System.out.println("Employee obejct pached successfully."+employee);
		
		Response response = new Response();
		response.setStatusCode("200");
		response.setStatusMessage("Employee Object Patched Successfully.");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(response);
	}
	
	
	
	
}
