package com.tech2java.eis.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * 
 * CREATE TABLE IF NOT EXISTS `employee` (
  `emp_id` int AUTO_INCREMENT  PRIMARY KEY,
  `emp_name` varchar(100) NOT NULL,
  `emp_email` varchar(100) NOT NULL,
  `emp_address` varchar(100) NOT NULL,
  `emp_type` varchar(10) NOT NULL,
  `created_dt` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_dt` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);
 *
 */

@Entity
@Table(name = "employee")
//if table name & class is same then no need to mention name= "employee"
public class Employee extends BaseEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name = "native",strategy = "native")
	private Integer empId;
	
	private String empName;
	
	private String empEmail;
	
	private String empAddress;
	
	@Enumerated(EnumType.STRING)
	private EMPLOYEE_TYPE empType;
	
	public enum EMPLOYEE_TYPE{
		CONTRACT,PERMINENT
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public EMPLOYEE_TYPE getEmpType() {
		return empType;
	}

	public void setEmpType(EMPLOYEE_TYPE empType) {
		this.empType = empType;
	}
	
}
