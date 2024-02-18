package com.project.presentation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.project.entity.EmployeeBookCatalogue;
import com.project.entity.LibraryBook;
import com.project.entity.LibraryBookReturn;
import com.project.exception.BookCatalogueException;
import com.project.exception.BookReturningException;
import com.project.exception.RecordNotFoundException;
import com.project.service.LibraryService;
import com.project.service.LibraryServiceImpl;

public class LibraryPresentationImpl implements LibraryPresentation {

	private LibraryService libraryService = new LibraryServiceImpl();

	@Override
	public void showMenu() {
		System.out.println("====================================================");
		System.out.println("Welcome to Sapient Library Management System");
		System.out.println("====================================================");
		System.out.println("1. Create a Book Catalogue");
		System.out.println("2. Issue the book");
		System.out.println("3. Show all Issues Books");
		System.out.println("4. Show all Books");
		System.out.println("5. Search the book by Book Type");
		System.out.println("6. Search the book by ID");
		System.out.println("7. Return the book");
		System.out.println("8. Exit");
	}

	@Override
	public void performMenu(int choice) {
		Scanner scanner = new Scanner(System.in);
		switch (choice) {
		case 1:
			try {
				System.out.println("Enter Employee Name : ");
				String empName = scanner.next();
				EmployeeBookCatalogue newEmployee = libraryService.createBookCatalogue(empName);
				if (newEmployee != null)
					System.out.println(
							"New Employee Book Catalogue Named " + newEmployee.getEmployeeName() + " is Created!");
			} catch (BookCatalogueException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Something went wrong please try again!");
			}
			break;
		case 2:
			try {
				System.out.println("Enter Book ID : ");
				int bookId = scanner.nextInt();
				if(libraryService.issueBook(bookId))
				{
					System.out.println("Book with book ID " + bookId +" has been Issued!");
				}
			} catch (BookCatalogueException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Something went wrong please try again!");
			}
			break;
		case 3:
			try {
				List<LibraryBook> allIssued = libraryService.showIssuedBookCatalogue();
				for (LibraryBook book : allIssued) {
					System.out.println(book);
				}
			} 
			catch (BookCatalogueException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Something went wrong please try again!");
			}
			break;
		case 4:
			try {
				List<LibraryBook> bookList = libraryService.fetchAllBook();
				for (LibraryBook book : bookList) {
					System.out.println(book);
				}
			} catch (RecordNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Something went wrong please try again!");
			}
			break;
		case 5:
			try {
				System.out.println("Enter Book Type : ");
				String bookType = scanner.next();
				List<LibraryBook> bookTypeList = libraryService.fetchBookByType(bookType);
				for (LibraryBook book : bookTypeList) {
					System.out.println(book);
				}
			} catch (RecordNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Something went wrong please try again!");
			}
			break;
		case 6:
			try {
				System.out.println("Enter Book ID : ");
				int bookId = scanner.nextInt();
				LibraryBook libbook = libraryService.fetchBookById(bookId);
				System.out.println(libbook);
			} catch (RecordNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Something went wrong please try again!");
			}
			break;
		case 7:
		{
			System.out.println("Enter Book ID : ");
			int bookId = scanner.nextInt();
			try {
				LibraryBookReturn libraryBookReturn = libraryService.returnBook(bookId, LocalDate.now());
				System.out.println(libraryBookReturn);
			} catch (BookReturningException e) {
				System.out.println("Caught exception "+e.getMessage());
			}
			break;
		}
		case 8:
			System.out.println("Thanks for using Sapient Library Management System");
			System.exit(0);
		
			break;
		default:
			System.out.println("Invalid Choice");
		}
	}
}