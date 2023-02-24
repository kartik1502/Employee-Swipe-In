package org.training.employeesmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.employeesmanagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
