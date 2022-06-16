package com.project.backend.exceptions;

public class ImageServiceException extends RuntimeException {

	private static final long serialVersionUID = -418958017331557617L;

	public ImageServiceException(String message) {
		super(message);
	}

}