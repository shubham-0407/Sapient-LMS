package com.project.client;

import java.util.List;

import com.project.entity.LibraryBook;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;

public class LibraryClient {

	public static void main(String[] args) {
		LibraryDao libraryDao = new LibraryDaoImpl();
		 List<LibraryBook> books = 	libraryDao.showBookByType("Technology");

		 for (LibraryBook libraryBook : books) {
			System.out.println(libraryBook);
		 }
		
	}
}
