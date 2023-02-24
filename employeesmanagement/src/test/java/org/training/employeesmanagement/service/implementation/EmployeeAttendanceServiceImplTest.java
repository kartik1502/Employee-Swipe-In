package org.training.employeesmanagement.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.employeesmanagement.entity.Employee;
import org.training.employeesmanagement.entity.EmployeeAttendance;
import org.training.employeesmanagement.exception.AccessDeniedException;
import org.training.employeesmanagement.exception.NoSuchEmployeeExists;
import org.training.employeesmanagement.repository.EmployeeAttendanceRepository;
import org.training.employeesmanagement.repository.EmployeeRepository;
import org.training.employeesmanagement.service.implmentation.EmployeeAttendanceServiceImpl;

@ExtendWith(SpringExtension.class)
public class EmployeeAttendanceServiceImplTest {

	@InjectMocks
	EmployeeAttendanceServiceImpl employeeAttendanceServiceImpl;


	@Mock
	EmployeeRepository employeeRepository;

	@Mock
	EmployeeAttendanceRepository employeeAttendanceRepository;

	@Test
	void testSearchEmployeeAttendanceNoSuchEmployeeExists() {
		when(employeeAttendanceRepository.findBySwipeDateBetweenAndEmployees((LocalDate) any(), (LocalDate) any(),
				(Employee) any())).thenThrow(new NoSuchEmployeeExists("No such employee exists"));

		Employee employee = new Employee();
		employee.setEmpEmail("divya@gmail.com");
		employee.setEmpId(674567);
		employee.setEmpName("divya");
		employee.setJoinDate(LocalDate.of(2023, 1,15));
		employee.setRole("admin");
		Optional<Employee> ofResult = Optional.of(employee);
		when(employeeRepository.findById((Integer) any())).thenReturn(ofResult);
		assertThrows(NoSuchEmployeeExists.class,
				() -> employeeAttendanceServiceImpl.searchemployeeattendance(674567,678567, "02-03-2020", "04-03-2020"));

	}


	@Test
	void testSearchEmployeeAttendanceAccessDeniedException() {
		Employee employee = new Employee();
		employee.setEmpEmail("divya@gmail.com");
		employee.setEmpId(674567);
		employee.setEmpName("divya");
		employee.setJoinDate(LocalDate.of(2023, 1, 14));
		employee.setRole("employee");
		Optional<Employee> ofResult = Optional.of(employee);
		when(employeeRepository.findById((Integer) any())).thenReturn(ofResult);
		assertThrows(AccessDeniedException.class,
				() -> employeeAttendanceServiceImpl.searchemployeeattendance(674567,674567, "01-03-2020", "02-03-2020"));
		verify(employeeRepository).findById((Integer) any());
	}


	@Test
	void testSearchEmployeeAttendance()
	{
		Employee emp=new Employee();
		EmployeeAttendance employeeAttendance=new EmployeeAttendance();
		EmployeeAttendance employeeAttendance2=new EmployeeAttendance();

		employeeAttendance.setEmployeeAttendanceId(1);
		emp.setEmpId(456789);
		emp.setEmpName("divya");
		emp.setEmpEmail("divya@gmail.com");
		emp.setJoinDate(LocalDate.of(2023, 1, 18));
		emp.setRole("ROLE_EMPLOYEE");
		employeeAttendance.setSwipeInTime(LocalTime.of(7, 30, 32));
		employeeAttendance.setSwipeOutTime(LocalTime.of(9, 29, 28));
		employeeAttendance.setSwipeDate(LocalDate.of(2023,2,19));

		employeeAttendance2.setEmployeeAttendanceId(2);
		emp.setEmpId(456789);
		emp.setEmpName("divya");
		emp.setEmpEmail("divya@gmail.com");
		emp.setJoinDate(LocalDate.of(2023, 1, 18));
		emp.setRole("ROLE_EMPLOYEE");
		employeeAttendance2.setSwipeInTime(LocalTime.of(10, 13, 24));
		employeeAttendance2.setSwipeOutTime(LocalTime.of(12, 47, 48));
		employeeAttendance2.setSwipeDate(LocalDate.of(2023,2,20));



		ArrayList<EmployeeAttendance> employeeAttendances = new ArrayList<>();
		employeeAttendances.add(employeeAttendance); 
		employeeAttendances.add(employeeAttendance2);

		when(employeeAttendanceRepository.findBySwipeDateBetweenAndEmployees(LocalDate.parse("2023-02-19"),LocalDate.parse("2023-02-20") ,emp)).thenReturn(employeeAttendances);
		assertEquals(2,employeeAttendances.size());


	}



}







