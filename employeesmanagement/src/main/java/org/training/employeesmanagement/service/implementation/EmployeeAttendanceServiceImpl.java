package org.training.employeesmanagement.service.implmentation;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.employeesmanagement.entity.Employee;
import org.training.employeesmanagement.entity.EmployeeAttendance;
import org.training.employeesmanagement.exception.AccessDeniedException;
import org.training.employeesmanagement.exception.NoSuchEmployeeExists;
import org.training.employeesmanagement.repository.EmployeeAttendanceRepository;
import org.training.employeesmanagement.repository.EmployeeRepository;
import org.training.employeesmanagement.service.EmployeeAttendanceService;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService{

	@Autowired	
	EmployeeAttendanceRepository employeeAttendanceRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeAttendance> searchemployeeattendance(int admniId, int empId, String startDate, String endDate) {

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate startdate=LocalDate.parse(startDate, dateFormatter);
		LocalDate enddate=LocalDate.parse(endDate, dateFormatter);


		Optional<Employee> employee = employeeRepository.findById(admniId);
		if(!"admin".equals(employee.get().getRole()))
		{
			throw new AccessDeniedException();                             
		}
		Optional<Employee> emp = employeeRepository.findById(empId);
		if(emp.isEmpty())
		{
			throw new NoSuchEmployeeExists();
		}

		return employeeAttendanceRepository.findBySwipeDateBetweenAndEmployees(startdate, enddate, emp.get());
	}
}