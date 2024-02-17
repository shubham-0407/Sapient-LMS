package com.project.persistence;

import java.time.LocalDate;
import java.util.List;

import com.project.entity.LibraryBook;


public interface LibraryDao {
	List<LibraryBook> getAllBook();
	List<LibraryBook> getBookByType(String bookType);
	LibraryBook getBookById(int bookId);
	boolean updateBookIssuedDate(int bookId, LocalDate issuedDate);
	boolean updateBookReturnDate(int bookId, LocalDate returnDate);

}
