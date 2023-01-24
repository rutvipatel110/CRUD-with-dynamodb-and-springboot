package com.example.dynamodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.dynamodb.entity.Employee;
import com.example.dynamodb.service.EmployeeServiceImpl;


/**
 * The Class EmployeeController.
 */
@RestController
public class EmployeeController {


	@Autowired
	private EmployeeServiceImpl employeeService;

	@PostMapping("/saveEmp")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeService.saveEmployee(employee);

	}

	@GetMapping("/getEmp")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();

	}
	
	@GetMapping("/getEmp/{id}/{role}")
	public Employee getEmployee(@PathVariable("id") String employeeId, @PathVariable("role") String role) {
		return employeeService.getEmployee(employeeId, role);

	}
	
	@DeleteMapping("/deleteEmp/{id}/{role}")
	public String deleteEmployee(@PathVariable("id") String employeeId , @PathVariable("role") String role) {
		return employeeService.deleteEmployee(employeeId, role);
	}

	@PutMapping("/updateEmp/{id}")
	public Employee updateEmployee(@PathVariable("id") String employeeId ,@RequestBody Employee employee ) {
		return employeeService.updateEmployee(employeeId, employee);
	}
		
	
	
	@GetMapping("/getDateEmp/{date}")
	public List<Employee> getEmployeeByDate(@PathVariable("date") String date){
		return employeeService.getEmployeeByDate(date);
	}
}
