package org.training.employeesmanagement.service.implmentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.employeesmanagement.entity.Employee;
import org.training.employeesmanagement.exception.EmployeeAlreadyExists;
import org.training.employeesmanagement.repository.EmployeeRepository;
import org.training.employeesmanagement.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee registerEmployee(Employee employee) {
		if (employeeRepository.findByEmpEmail(employee.getEmpEmail()) != null) {
			logger.error("Employee already exists Exception is thrown");
			throw new EmployeeAlreadyExists("Employee already registered");
		}
		Employee registeredEmployee = employeeRepository.save(employee);
		logger.info("Employee registered successfully");
		return registeredEmployee;
	}


}