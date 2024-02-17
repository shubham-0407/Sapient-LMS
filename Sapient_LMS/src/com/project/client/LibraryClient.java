package com.project.client;

import java.time.LocalDate;
import java.util.List;

import com.project.entity.LibraryBook;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;
import com.project.service.LibraryService;
import com.project.service.LibraryServiceImpl;

public class LibraryClient {

	public static void main(String[] args) {
		LibraryDao libraryDao = new LibraryDaoImpl();
		// 
		LibraryService libraryService = new LibraryServiceImpl();

		
		//  List<LibraryBook> books = 	libraryDao.getBooksByType(null);
		
		// libraryDao.updateBookIssueStatus(102,LocalDate.now());
		 libraryDao.updateBookReturnStatus(102,LocalDate.now().plusDays(10));
		 

		 LibraryBook book = 	libraryDao.findBookByBookId(102);
		 System.out.println(book);

		long overdue = libraryService.calculateOverdueDays(102);
System.out.println(overdue);
		//  for (LibraryBook libraryBook : books) {
		// 	System.out.println(libraryBook);
		//  }
		
	}
}
