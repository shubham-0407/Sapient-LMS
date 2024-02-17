package com.project.client;

import java.time.LocalDate;
import java.util.List;

import com.project.entity.EmployeeBookCatalogue;
import com.project.entity.LibraryBook;
import com.project.entity.LibraryBookReturn;
import com.project.exception.BookCatalogueException;
import com.project.exception.BookReturningException;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;
import com.project.service.LibraryService;
import com.project.service.LibraryServiceImpl;

public class ClientDemo {

	public static void main(String[] args) {
		LibraryDao libraryDao = new LibraryDaoImpl();
		LibraryService libraryService = new LibraryServiceImpl();
		// EmployeeBookCatalogue employeeBookCatalogue = new EmployeeBookCatalogue();
//		 List<LibraryBook> booksList = 	libraryDao.showAllBook();
//		 for (LibraryBook libraryBook : booksList) {
//				System.out.println(libraryBook);
//			 }
		System.out.println("**************************************************");
		List<LibraryBook> books = libraryDao.getBookByType("Management");

		for (LibraryBook libraryBook : books) {
			System.out.println(libraryBook);
		}
		System.out.println("**************************************************");
		int input = 2;
		String name = "Sk";
		try {
			EmployeeBookCatalogue employeeBookCatalogue = null;
			employeeBookCatalogue = libraryService.createBookCatalogue(name);
			// libraryService.issueBook(books.get(input).getBookId());
			libraryService.issueBook(books.get(0).getBookId());
			libraryService.issueBook(books.get(1).getBookId());
			System.out.println("Issued Book Details *******");
			System.out.println("Employee Name: " + employeeBookCatalogue.getEmployeeName());
			System.out.println("List of issued books: ");
			List<LibraryBook> issuedbooks = libraryService.showIssuedBookCatalogue();
			for (LibraryBook libraryBook : issuedbooks) {
				System.out.println(libraryBook);
			}
			System.out.println("**********************REturning book");
			 int bookId = books.get(0).getBookId();
			 LocalDate returnDate = LocalDate.of(2024, 2, 25);
			 LibraryBookReturn libraryBookReturn = libraryService.returnBook(bookId, returnDate);
			 System.out.println("NAme: "+libraryBookReturn.getEmployeeName());
			 System.out.println("Type: "+libraryBookReturn.getBookType());
			 System.out.println("Fine: "+libraryBookReturn.getLateFine());
			 System.out.println("Issued date: "+libraryBookReturn.getIssuedDate());
			 System.out.println("Return dae: "+libraryBookReturn.getReturnDate());
			 System.out.println("**********************Display left book");
			 System.out.println(employeeBookCatalogue.getIssuedBooks());
		} catch (BookCatalogueException | BookReturningException e) {
			System.out.println(e.getMessage());
		}

	}

}
