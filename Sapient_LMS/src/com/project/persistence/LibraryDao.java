package com.project.persistence;

import java.time.LocalDate;
import java.util.List;

import com.project.entity.LibraryBook;


public interface LibraryDao {
	
	List<LibraryBook> getAllBooks();
	List<LibraryBook> getBooksByType(String bookType);

	// Issue Book
	int updateBookIssueStatus(int id,LocalDate issueDate);

	// Returning Book
	LibraryBook findBookByBookId(int id);
	int updateBookReturnStatus(int id,LocalDate returnDate);
	



	

}
