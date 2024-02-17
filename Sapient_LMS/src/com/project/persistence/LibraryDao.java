package com.project.persistence;

import java.time.LocalDate;
import java.util.List;

import com.project.entity.LibraryBook;


public interface LibraryDao {
	
	List<LibraryBook> showAllBook();
	List<LibraryBook> showBookByType(String bookType);
	LibraryBook showBookById(int bookId);
	boolean updateBookIssuedDate(int bookId, LocalDate issuedDate);
	boolean updateBookReturnDate(int bookId, LocalDate returnDate);

}
