package org.training.employeesmanagement.exception;

public class EmployeeAlreadyExists extends RuntimeException{

private static final long serialVersionUID = 1L;

private String message;

public EmployeeAlreadyExists(String message) {
super(message);
}

public EmployeeAlreadyExists() {
super();
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}
}