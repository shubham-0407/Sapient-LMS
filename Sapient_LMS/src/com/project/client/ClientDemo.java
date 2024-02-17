package com.project.client;

import java.util.List;

import com.project.entity.EmployeeBookCatalogue;
import com.project.entity.LibraryBook;
import com.project.exception.BookCatalogueException;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;
import com.project.service.LibraryService;
import com.project.service.LibraryServiceImpl;

public class ClientDemo {

	public static void main(String[] args) {
		LibraryDao libraryDao = new LibraryDaoImpl();
		LibraryService libraryService = new LibraryServiceImpl();
		//EmployeeBookCatalogue employeeBookCatalogue = new EmployeeBookCatalogue();
//		 List<LibraryBook> booksList = 	libraryDao.showAllBook();
//		 for (LibraryBook libraryBook : booksList) {
//				System.out.println(libraryBook);
//			 }
		 System.out.println("**************************************************");
		 List<LibraryBook> books = 	libraryDao.showBookByType("Technology");

		 for (LibraryBook libraryBook : books) {
			System.out.println(libraryBook);
		 }
		 System.out.println("**************************************************");
		 int input = 2;
		 String name = "Shubham";
		 try {
			 libraryService.createBookCatalogue(name);
			// libraryService.issueBook(books.get(input).getBookId());
			 libraryService.issueBook(books.get(3).getBookId());
//			 libraryService.issueBook(books.get(1).getBookId());
			 System.out.println("Issued Book Details *******");
			 EmployeeBookCatalogue employeeBookCatalogue = libraryService.showIssuedBookCatalogue();
			 System.out.println("Employee Name: "+employeeBookCatalogue.getEmployeeName());
			 System.out.println("List of issued books: "); 
			 List<LibraryBook> issuedbooks = 	employeeBookCatalogue.getIssuedBooks();
			 if(issuedbooks.isEmpty()) {
				 System.out.println("No issued book");
			 }
			 for (LibraryBook libraryBook : issuedbooks) {
				System.out.println(libraryBook);
			 }
		} catch (BookCatalogueException e) {
			System.out.println(e.getMessage());
		}
		
		 

	}

}
