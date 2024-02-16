package com.project.entity;

import java.time.LocalDate;

public class LibraryBookReturn {
	private String employeeName;
	private String bookType;
	private LocalDate issuedDate;
	private LocalDate returnDate;
	private double lateFine;
	public LibraryBookReturn(String employeeName, String bookType, LocalDate issuedDate, LocalDate returnDate,
			double lateFine) {
		super();
		this.employeeName = employeeName;
		this.bookType = bookType;
		this.issuedDate = issuedDate;
		this.returnDate = returnDate;
		this.lateFine = lateFine;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public String getBookType() {
		return bookType;
	}
	public LocalDate getIssuedDate() {
		return issuedDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public double getLateFine() {
		return lateFine;
	}
}
