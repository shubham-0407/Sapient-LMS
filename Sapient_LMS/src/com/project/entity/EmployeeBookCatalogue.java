package com.project.entity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBookCatalogue {
    private String employeeName;
    private List<LibraryBook> issuedBooks;
    
    public EmployeeBookCatalogue(String employeeName) {
		super();
		this.employeeName = employeeName;
		this.issuedBooks = new ArrayList<>();;
	}

	public String getEmployeeName() {
		return employeeName;
	}
 
	public List<LibraryBook> getIssuedBooks() {
		return issuedBooks;
	}
 
	public void issueBook(LibraryBook book) {
        issuedBooks.add(book);
    }

    public void returnBook(LibraryBook book) {
        issuedBooks.remove(book);
    }
}

