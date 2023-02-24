package org.training.employeesmanagement.controller;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.training.employeesmanagement.dto.EmployeeDto;
import org.training.employeesmanagement.entity.Employee;
import org.training.employeesmanagement.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> registerEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		Employee employee = new Employee();
		int randomNumber = new Random().nextInt((999999 - 100000) + 1) + 100000;

		employee.setEmpId(randomNumber);
		employee.setEmpName(employeeDto.getEmpName());
		employee.setEmpEmail(employeeDto.getEmpEmail());
		employee.setRole(employeeDto.getRole());
		return new ResponseEntity<>(employeeService.registerEmployee(employee), HttpStatus.OK);
	}
  
}
