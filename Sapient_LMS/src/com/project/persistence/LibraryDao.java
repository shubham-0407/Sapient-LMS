package com.project.persistence;

import java.util.List;

import com.project.entity.LibraryBook;
import com.project.exception.ExecutionErrorException;
import com.project.exception.RecordNotFoundException;

public interface LibraryDao {
	
	List<LibraryBook> showAllBook() throws  ExecutionErrorException, RecordNotFoundException;
	List<LibraryBook> showBookByType(String bookType) throws  ExecutionErrorException, RecordNotFoundException;
	

}
