package com.project.service;

import java.util.List;

import com.project.entity.EmployeeBookCatalogue;
import com.project.entity.LibraryBook;
import com.project.exception.BookCatalogueException;
import com.project.exception.RecordNotFoundException;

public interface LibraryService {
	
	/* Method for issuing books*/
	boolean createBookCatalogue(String name) throws BookCatalogueException;
	boolean issueBook(int bookId) throws BookCatalogueException;
	EmployeeBookCatalogue showIssuedBookCatalogue()throws BookCatalogueException;

    // Return
    long calculateOverdueDays(int id);

	/*Method for showing book list*/
	List<LibraryBook> fetchAllBook() throws RecordNotFoundException;
	List<LibraryBook> fetchBookByType(String bookType) throws RecordNotFoundException;
	LibraryBook fetchBookById(int bookId) throws RecordNotFoundException;
 
}
