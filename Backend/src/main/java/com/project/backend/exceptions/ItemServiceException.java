package com.project.backend.exceptions;

public class ItemServiceException extends RuntimeException {

	private static final long serialVersionUID = -4649373469110097313L;

	public ItemServiceException(String message) {
		super(message);
	}

}