package com.project.entity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBookCatalogue {
	private String employeeName;
	private List<LibraryBook> issuedBooks;
	private boolean catalogueCreated;

	public EmployeeBookCatalogue(String employeeName) {
		super();
		this.employeeName = employeeName;
		this.issuedBooks = new ArrayList<>();
		this.catalogueCreated = true;
	}

	public boolean isCatalogueCreated() {
		return catalogueCreated;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public List<LibraryBook> getIssuedBooks() {
		return issuedBooks;
	}

	public boolean setIssuedBooks(LibraryBook issuedBooks) {
		if (!this.issuedBooks.contains(issuedBooks)) {
			this.issuedBooks.add(issuedBooks);
			return true;
		}
		return false;
	}

	public boolean returnBook(LibraryBook returnBook) {
		if (this.issuedBooks.contains(returnBook)) {
			this.issuedBooks.remove(returnBook);
			return true;
		}
		return false;
	}

}
