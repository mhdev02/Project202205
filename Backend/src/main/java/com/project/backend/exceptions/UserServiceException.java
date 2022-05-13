package com.project.backend.exceptions;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 1658360500022018932L;

	public UserServiceException(String message) {
		super(message);
	}

}