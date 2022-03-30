package com.crud_read_and_create.service.exception;

public class DuplicateException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateException(String msg) {
		super(msg);
	}
}
