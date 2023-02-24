package org.training.employeesmanagement.service;

import java.util.List;


import org.training.employeesmanagement.entity.EmployeeAttendance;


public interface EmployeeAttendanceService {


	List<EmployeeAttendance> searchemployeeattendance(int admniId, int empId, String startDate, String endDate);

}
