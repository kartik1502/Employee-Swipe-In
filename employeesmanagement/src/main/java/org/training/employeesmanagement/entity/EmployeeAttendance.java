package org.training.employeesmanagement.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class EmployeeAttendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeAttendanceId;
	
	@ManyToOne
	private Employee employees;
	
	private LocalTime swipeInTime;
	
	private LocalTime swipeOutTime;
	
	@CreationTimestamp
	private LocalDate swipeDate;
	
}
