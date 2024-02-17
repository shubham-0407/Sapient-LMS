package com.project.entity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBookCatalogue {
    private String employeeName;
    private List<LibraryBook> issuedBooks;
    private boolean issuedSuccess;
    private boolean catalogueCreated;
    private boolean returnSuccess;

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
		issuedSuccess = this.issuedBooks.add(issuedBooks);
		if(issuedSuccess) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean returnBook(LibraryBook returnBook) {
		returnSuccess = this.issuedBooks.remove(returnBook);
		if(returnSuccess) {
			return true;
		}else {
			return false;
		}
	}

}

