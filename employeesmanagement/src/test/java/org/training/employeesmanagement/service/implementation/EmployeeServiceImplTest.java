package org.training.employeesmanagement.service.implementation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.employeesmanagement.entity.Employee;
import org.training.employeesmanagement.exception.EmployeeAlreadyExists;
import org.training.employeesmanagement.repository.EmployeeRepository;
import org.training.employeesmanagement.service.implmentation.EmployeeServiceImpl;



@ExtendWith(SpringExtension.class)
public class EmployeeServiceImplTest {
	
	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;

	@Mock
	EmployeeRepository employeeRepository;
	
	@Test
	void testEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setEmpName("nadiya");
	    employee.setEmpEmail("nadiyan@gmail.com");
		employee.setRole("admin");
		
		Mockito.when(employeeServiceImpl.registerEmployee(employee)).thenReturn(employee);
		assertSame(employee, employeeServiceImpl.registerEmployee(employee));
	}
	@Test
	void testEmployee2() {
		Employee employee = new Employee();
		employee.setEmpName("saniya");
	    employee.setEmpEmail("saniyan@gmail.com");
		employee.setRole("employee");
		
		Mockito.when(employeeServiceImpl.registerEmployee(employee)).thenReturn(employee);
		assertSame(employee, employeeServiceImpl.registerEmployee(employee));
	}
	
	@Test
	void testEmployeeAlreadyExists() {
		Employee employee = new Employee();
		employee.setEmpName("nadiya");
	    employee.setEmpEmail("nadiyan@gmail.com");
		employee.setRole("ROLE_ADMIN");
		
		when(employeeServiceImpl.registerEmployee(employee)).thenThrow(new EmployeeAlreadyExists());
		assertThrows(EmployeeAlreadyExists.class,() -> employeeServiceImpl.registerEmployee(employee));
	}
}
