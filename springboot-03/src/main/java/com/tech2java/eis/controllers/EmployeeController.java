package com.tech2java.eis.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.tech2java.eis.domain.Response;
import com.tech2java.eis.entity.Employee;
import com.tech2java.eis.repository.EmployeeRepository;

@RestController
@RequestMapping(path="/api/employees",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping
	public List<Employee> getEmployees() {
		
		List<Employee> employees=new ArrayList<>();
		
		Iterable<Employee> emIterable=employeeRepository.findAll();
		Iterator<Employee> itr=emIterable.iterator();
		
		
		while(itr.hasNext()) {
			Employee employee=itr.next();
			employees.add(employee);
		}
		return employees;
		
	}
	
	
	@GetMapping("/getEmployeeByEmail")
	public Employee getEmployeeByName(@RequestParam("empEmail") String empEmail) {
		
		Employee employee=employeeRepository.findEmployeeByEmpEmail(empEmail);
		return employee;
	}
	
	
	
	@PostMapping("/saveEmp")
	public ResponseEntity<Response> saveEmployee(@RequestHeader("client") String client,
												 @RequestBody Employee employee) {
		//@Valid- need to look
		System.out.println("Request is comming from client::"+client);
		
		System.out.println("Employee::"+employee);
		//need to write logic to save object into database.
		employeeRepository.save(employee);
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
		employeeRepository.deleteById(employee.getEmpId());
		System.out.println("Employee obejct deleted successfully."+employee);
		
		Response response = new Response();
		response.setStatusCode("200");
		response.setStatusMessage("Employee Object Deleted Successfully.");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(response);
	}
	
	@PatchMapping("/updateEmail")
	public ResponseEntity<Response> patchEmployee(RequestEntity<Employee> requestEntity) {
		
		HttpHeaders httpHeaders=requestEntity.getHeaders();
		httpHeaders.forEach((key,value)->{
			System.out.println("KEY="+key +"::VALUE=>"+value.stream().collect(Collectors.joining("|")));
		});
		
		Employee employee=requestEntity.getBody();
		System.out.println("Employee ID::"+employee.getEmpId());
		
		
		Optional<Employee> eOptional=employeeRepository.findById(employee.getEmpId());
		if(eOptional.isPresent()) {
			employee.setEmpEmail("dummy@gmail.com");
			employeeRepository.save(employee);
			System.out.println("Employee obejct pached successfully."+employee);
		}else {
			Response response = new Response();
			response.setStatusCode("401");
			response.setStatusMessage("Employee Not Found in the Database.");

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(response);
		}
		
		//Get Employee by ID
		//then check if employee available if yes,
		// then update the age else,
		//send error message with BAD request and Employee not found status message.
		
		//need to write logic to delete object into database. //deleteById
		
		
		Response response = new Response();
		response.setStatusCode("200");
		response.setStatusMessage("Employee Object Patched Successfully.");

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(response);
	}
	
	
	
	
}
