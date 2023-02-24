package org.training.employeesmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.employeesmanagement.entity.EmployeeAttendance;
import org.training.employeesmanagement.service.EmployeeAttendanceService;

@RestController
@RequestMapping("/employee-attendance")
public class EmployeeAttendanceController {

	@Autowired
	private EmployeeAttendanceService service;

	@PostMapping
	public ResponseEntity<EmployeeAttendance> addDetails(@RequestParam int emplId) {
		return new ResponseEntity<>(service.addDetails(emplId), HttpStatus.OK);
	}
}
