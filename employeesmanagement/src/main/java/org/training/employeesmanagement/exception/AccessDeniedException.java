package org.training.employeesmanagement.exception;

public class AccessDeniedException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String message;

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException()  {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}