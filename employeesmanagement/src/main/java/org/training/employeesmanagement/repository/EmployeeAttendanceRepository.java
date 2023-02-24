package org.training.employeesmanagement.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.employeesmanagement.entity.Employee;
import org.training.employeesmanagement.entity.EmployeeAttendance;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Integer> {

	EmployeeAttendance findEmployeeAttendanceBySwipeDateAndEmployees(LocalDate now, Employee employee);

}
