package org.training.employeesmanagement.service.implmentation;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.training.employeesmanagement.entity.Employee;
import org.training.employeesmanagement.entity.EmployeeAttendance;
import org.training.employeesmanagement.exception.NoSuchEmployeeExists;
import org.training.employeesmanagement.repository.EmployeeAttendanceRepository;
import org.training.employeesmanagement.repository.EmployeeRepository;

@ExtendWith(SpringExtension.class)
public class EmployeeAttendanceServiceImplTest {

	@InjectMocks
	private EmployeeAttendanceServiceImpl employeeAttendanceServiceImpl;

	@Mock
	private EmployeeAttendanceRepository employeeAttendanceRepository;

	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	void testAddDetailsAttendanceNull() {

		Employee employee = new Employee();
		employee.setEmpEmail("kartikkulkarni1411@gmail.com");
		employee.setEmpId(845129);
		employee.setEmpName("Karthik");
		employee.setJoinDate(LocalDate.ofEpochDay(1L));
		employee.setRole("ROLE_EMPLOYEE");

		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		employeeAttendance.setEmployeeAttendanceId(3);
		employeeAttendance.setEmployees(employee);
		employeeAttendance.setSwipeDate(LocalDate.ofEpochDay(1L));
		employeeAttendance.setSwipeInTime(LocalTime.of(1, 1));
		employeeAttendance.setSwipeOutTime(null);

		when(employeeAttendanceRepository.save((EmployeeAttendance) any())).thenReturn(employeeAttendance);
		when(employeeAttendanceRepository.findEmployeeAttendanceBySwipeDateAndEmployees((LocalDate) any(),
				(Employee) any())).thenReturn(null);

		Optional<Employee> result = Optional.of(employee);
		when(employeeRepository.findById((Integer) any())).thenReturn(result);
		assertSame(employeeAttendance, employeeAttendanceServiceImpl.addDetails(845129));
	}

	@Test
	void testAddDetailsNoSuchEmployee() {

		Employee employee = new Employee();
		employee.setEmpEmail("kartikkulkarni1411@gmail.com");
		employee.setEmpId(845129);
		employee.setEmpName("Karthik");
		employee.setJoinDate(LocalDate.ofEpochDay(1L));
		employee.setRole("ROLE_EMPLOYEEE");

		when(employeeRepository.findById((Integer) any())).thenThrow(new NoSuchEmployeeExists());
		assertThrows(NoSuchEmployeeExists.class, () -> employeeAttendanceServiceImpl.addDetails(845129));
	}

	@Test
	void testAddDetailsSwipeOutTimeNull() {

		Employee employee = new Employee();
		employee.setEmpEmail("kartikkulkarni1411@gmail.com");
		employee.setEmpId(845129);
		employee.setEmpName("Karthik");
		employee.setJoinDate(LocalDate.ofEpochDay(1L));
		employee.setRole("ROLE_EMPLOYEE");

		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		employeeAttendance.setEmployeeAttendanceId(3);
		employeeAttendance.setEmployees(employee);
		employeeAttendance.setSwipeDate(LocalDate.ofEpochDay(1L));
		employeeAttendance.setSwipeInTime(LocalTime.of(1, 1));
		employeeAttendance.setSwipeOutTime(null);

		when(employeeAttendanceRepository.findEmployeeAttendanceBySwipeDateAndEmployees((LocalDate) any(),
				(Employee) any())).thenReturn(employeeAttendance);

		EmployeeAttendance employeeAttendance1 = mock(EmployeeAttendance.class);
		when(employeeAttendance1.getSwipeOutTime()).thenReturn(null);
		employeeAttendance.setSwipeOutTime(LocalTime.of(1, 1));
		when(employeeAttendanceRepository.save((EmployeeAttendance) any())).thenReturn(employeeAttendance);

		Optional<Employee> result = Optional.of(employee);
		when(employeeRepository.findById((Integer) any())).thenReturn(result);
		assertSame(employeeAttendance, employeeAttendanceServiceImpl.addDetails(845129));
	}

	@Test
	void testAddDetailsSwipeOutInTimeNotNull() {

		Employee employee = new Employee();
		employee.setEmpEmail("kartikkulkarni1411@gmail.com");
		employee.setEmpId(845129);
		employee.setEmpName("Karthik");
		employee.setJoinDate(LocalDate.ofEpochDay(1L));
		employee.setRole("ROLE_EMPLOYEE");

		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		employeeAttendance.setEmployeeAttendanceId(3);
		employeeAttendance.setEmployees(employee);
		employeeAttendance.setSwipeDate(LocalDate.ofEpochDay(1L));
		employeeAttendance.setSwipeInTime(LocalTime.of(1, 1));
		employeeAttendance.setSwipeOutTime(LocalTime.of(1, 8));

		when(employeeAttendanceRepository.findEmployeeAttendanceBySwipeDateAndEmployees((LocalDate) any(),
				(Employee) any())).thenReturn(employeeAttendance);

		EmployeeAttendance employeeAttendance1 = mock(EmployeeAttendance.class);
		when(employeeAttendance1.getSwipeInTime()).thenReturn(LocalTime.of(1, 1));
		when(employeeAttendance1.getSwipeOutTime()).thenReturn(LocalTime.of(1, 8));
		employeeAttendance.setSwipeInTime(LocalTime.now());
		employeeAttendance.setSwipeOutTime(null);
		when(employeeAttendanceRepository.save((EmployeeAttendance) any())).thenReturn(employeeAttendance);

		Optional<Employee> result = Optional.of(employee);
		when(employeeRepository.findById((Integer) any())).thenReturn(result);
		assertSame(employeeAttendance, employeeAttendanceServiceImpl.addDetails(845129));
	}
}
