package org.training.employeesmanagement.service.implmentation;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.employeesmanagement.entity.Employee;
import org.training.employeesmanagement.entity.EmployeeAttendance;
import org.training.employeesmanagement.exception.NoSuchEmployeeExists;
import org.training.employeesmanagement.repository.EmployeeAttendanceRepository;
import org.training.employeesmanagement.repository.EmployeeRepository;
import org.training.employeesmanagement.service.EmployeeAttendanceService;

@Service
public class EmployeeAttendanceServiceImpl implements EmployeeAttendanceService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeAttendanceRepository attendanceRepository;
	
	Logger logger = LoggerFactory.getLogger(EmployeeAttendanceServiceImpl.class);

	@Override
	public EmployeeAttendance addDetails(int emplId) {

		Optional<Employee> employee = employeeRepository.findById(emplId);
		if (employee.isEmpty()) {
			logger.info("No such employee exception handled");
			throw new NoSuchEmployeeExists("Employee with emplId: " + emplId + " dose not exists");
		}
		EmployeeAttendance attendance = attendanceRepository
				.findEmployeeAttendanceBySwipeDateAndEmployees(LocalDate.now(), employee.get());
		if (attendance == null) {
			EmployeeAttendance employeeAttendance = new EmployeeAttendance();
			employeeAttendance.setEmployees(employee.get());
			employeeAttendance.setSwipeDate(LocalDate.now());
			employeeAttendance.setSwipeInTime(LocalTime.now());
			employeeAttendance.setSwipeOutTime(null);
			return attendanceRepository.save(employeeAttendance);
		} else {
			attendance.setSwipeOutTime(LocalTime.now());
			return attendanceRepository.save(attendance);
		}
	}
	
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
