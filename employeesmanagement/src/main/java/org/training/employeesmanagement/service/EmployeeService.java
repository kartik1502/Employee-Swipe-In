package org.training.employeesmanagement.service;

import org.springframework.stereotype.Service;
import org.training.employeesmanagement.entity.Employee;

@Service
public interface EmployeeService {
	Employee registerEmployee(Employee employee);

}
