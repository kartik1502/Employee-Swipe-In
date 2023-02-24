package org.training.employeesmanagement.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
public class Employee {
	
	@Id
	private int empId;

	@NotNull
	@Pattern(regexp = "[a-zA-Z ]+", message = "Invalid employee name")
	private String empName;
	
	@NotNull
	@Pattern(regexp = "", message = "Invalid email Id")
	private String empEmail;
	
	private LocalDate joinDate;
	
	@NotNull
	@Pattern(regexp = "[a-zA-Z_ ]+", message = "Invalid Role")
	private String role;
}
