package org.training.employeesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.employeesmanagement.entity.EmployeeAttendance;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Integer> {



}
