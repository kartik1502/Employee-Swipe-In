package org.training.employeesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.employeesmanagement.entity.EmployeeAttendance;
import org.training.employeesmanagement.service.EmployeeAttendanceService;



@RestController
@RequestMapping("/")
public class EmployeeAttendanceController {

	@Autowired
	public EmployeeAttendanceService employeeAttendanceService;


	@GetMapping("/")
	public ResponseEntity<List<EmployeeAttendance>> searchemployeeattendance(@RequestParam int admniId,@RequestParam int empId, @RequestParam String startDate, @RequestParam String endDate){
		return new ResponseEntity<>(employeeAttendanceService.searchemployeeattendance(admniId, empId, startDate, endDate), HttpStatus.OK);
	}

}
