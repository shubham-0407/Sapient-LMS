package com.project.persistence;

import java.util.List;

import com.project.entity.LibraryBook;

public interface LibraryDao {
	List<LibraryBook> getAllBook();
	List<LibraryBook> getBookByType(String bookType);
	LibraryBook getBookById(int bookId);
	boolean updateBookIssuedStatus(int bookId, boolean issuedStatus);
	 
}
