package com.project.service;

import java.time.LocalDate;
import java.util.List;

import com.project.entity.EmployeeBookCatalogue;
import com.project.entity.LibraryBook;
import com.project.entity.LibraryBookReturn;
import com.project.exception.BookCatalogueException;
import com.project.exception.BookReturningException;
import com.project.exception.RecordNotFoundException;

public interface LibraryService {
	
	/* Method for issuing books*/
	EmployeeBookCatalogue createBookCatalogue(String name) throws BookCatalogueException;
	boolean issueBook(int bookId) throws BookCatalogueException;
	List<LibraryBook> showIssuedBookCatalogue()throws BookCatalogueException;

    /* Method for returning books*/
    long calculateOverdueDays(LocalDate issuedDate,LocalDate returningDate);
    double calculateLateFine(String bookType, long overDays) throws BookReturningException;
    LibraryBookReturn returnBook(int bookId, LocalDate returningDate) throws BookReturningException;
    
	/*Method for showing book list*/
	List<LibraryBook> fetchAllBook() throws RecordNotFoundException;
	List<LibraryBook> fetchBookByType(String bookType) throws RecordNotFoundException;
	LibraryBook fetchBookById(int bookId) throws RecordNotFoundException;
 
}
