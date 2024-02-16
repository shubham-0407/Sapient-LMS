package com.project.client;

import java.util.List;

import com.project.entity.LibraryBook;
import com.project.exception.ExecutionErrorException;
import com.project.exception.RecordNotFoundException;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;

public class LibraryClient {

	public static void main(String[] args) {
		LibraryDao libraryDao = new LibraryDaoImpl();

		
		 List<LibraryBook> books = 	libraryDao.showBookByType(null);

		 for (LibraryBook libraryBook : books) {
			System.out.println(libraryBook);
		 }
		
	}
}
