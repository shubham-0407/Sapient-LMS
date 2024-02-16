package com.project.exception;

public class BookNotFoundException extends Exception {
	 private final static String notFoundErrorMessage = "Exception occured: \tNo Such Book Available";
	public BookNotFoundException() {
		super(notFoundErrorMessage);
	}
	
}
