package com.project.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.project.entity.EmployeeBookCatalogue;
import com.project.entity.LibraryBook;
import com.project.entity.LibraryBookReturn;
import com.project.entity.LibraryBookType;
import com.project.exception.BookCatalogueException;
import com.project.exception.BookReturningException;
import com.project.exception.RecordNotFoundException;
import com.project.service.LibraryService;
import com.project.service.LibraryServiceImpl;

public class LibraryPresentationImpl implements LibraryPresentation {

	Scanner scanner = new Scanner(System.in);
	private LibraryService libraryService = new LibraryServiceImpl();

	// Method for creating first time book catalogue
	@Override
	public void loginMenu() {
		try {
			System.out.print("Enter Your Name : ");
			String empName = scanner.next();
			EmployeeBookCatalogue newEmployee = libraryService.createBookCatalogue(empName);
			if (newEmployee != null)
				System.out
						.println("New Employee Book Catalogue Named " + newEmployee.getEmployeeName() + " is Created!");
		} catch (BookCatalogueException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong please try again!");
		}
	}

	// Method to perform library operation
	@Override
	public void libraryOperationMenu() {
		System.out.println("\n====================================================");
		System.out.println("Welcome to Sapient Library Management System");
		System.out.println("====================================================");
		System.out.println("Press 1. To issue a book");
		System.out.println("Press 2. To return a book");
		System.out.println("Press 3. Show book catalogue");
		System.out.println("Press 4. Exit");
	}

	@Override
	public void libraryOperationPerformMenu(int choice) {
		switch (choice) {
		case 1:
			System.out.println("\nPress 1: Search all book\nPress 2: Search book by type");
			System.out.print("Entered input: ");
			int searchInput = scanner.nextInt();
			if (searchInput == 1) {
				showAllBookListMenu();
			} else if (searchInput == 2) {
				showBookTypeMenu();
			} else {
				System.out.println("Invalid choice!!\nPlease try again!");
				libraryOperationMenu();
			}
			break;
		case 2:
			returnBookCatalogue();
			break;
		case 3:
			showIssuedBookCatalogue();
			break;
		case 4:
			System.out.println("Thanks for using Sapient Library Management System");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Choice");
		}
	}

	@Override
	public void showAllBookListMenu() {
		try {
			System.out.println("\n=========================");
			System.out.println("List of Available Books");
			System.out.println("===========================");
			int position = 1;
			List<LibraryBook> bookList = libraryService.fetchAllBook();
			if (bookList.size() > 0) {
				for (LibraryBook book : bookList) {
					System.out.print("Book " + position + ": ");
					System.out.println(book.getBookName() + " By " + book.getBookAuthor() + " || " + "Book Type: "
							+ book.getBookType());
					position++;
				}
				System.out.print("Entered input is: ");
				int selectBookInput = scanner.nextInt();
				int bookId = bookList.get(selectBookInput - 1).getBookId();
				issueBook(bookId);
			}
		} catch (RecordNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong please try again!");
		}
	}

	@Override
	public void issueBook(int bookId) {
		try {
			if (libraryService.issueBook(bookId)) {
				System.out.println("Book with book ID " + bookId + " has been Issued!");
			}
		} catch (BookCatalogueException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong please try again!");
		}
	}

	@Override
	public void showBookTypeMenu() {
		System.out.println("\n=========================");
		System.out.println("List of Available Book Type");
		System.out.println("===========================");
		System.out.println("Press 1. Management");
		System.out.println("Press 2. Technology");
		System.out.println("Press 3. Data Analytics");
		System.out.println("Entered input is: ");
		int selectedBookType = scanner.nextInt();
		String bookType = "";
		LibraryBookType libraryBookType = new LibraryBookType();
		if (selectedBookType == 1) {
			bookType = libraryBookType.getManagementBookType();
		} else if (selectedBookType == 2) {
			bookType = libraryBookType.getTechnologyBookType();
		} else if (selectedBookType == 3) {
			bookType = libraryBookType.getDataAnalyticsBookType();
		} else {
			System.out.println("Invalid input entered\n");
			showBookTypeMenu();
		}
		showBookListByType(bookType);
	}

	@Override
	public void showBookListByType(String bookType) {
		try {
			System.out.println("\n=========================");
			System.out.println("List of Available Books of " + bookType.toUpperCase());
			System.out.println("===========================");
			System.out.println("Book type: "+bookType);
			int position = 1;
			List<LibraryBook> bookListByType = libraryService.fetchBookByType(bookType);
			for (LibraryBook book : bookListByType) {
				System.out.print("Book " + position + ": ");
				System.out.println(book.getBookName() + " By " + book.getBookAuthor());
				position++;
			}
			System.out.print("Entered input is: ");
			int selectBookInput = scanner.nextInt();
			int bookId = bookListByType.get(selectBookInput - 1).getBookId();
			issueBook(bookId);
		} catch (RecordNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong please try again!");
		}

	}

	@Override
	public void returnBookCatalogue() {
		try {
			int position = 1;
			List<LibraryBook> issuedBookList = libraryService.showIssuedBookCatalogue();
			System.out.println("\n=========================================");
			System.out.println("List of Issued Books for Your Account");
			System.out.println("============================================");
			for (LibraryBook issuedBook : issuedBookList) {
				LocalDate issuedDate = issuedBook.getIssuedDate();
				LocalDate defaultReturnDate = issuedBook.getReturnDate();
				System.out.print("Book " + position + ": ");
				System.out.println(issuedBook.getBookName() + " By " + issuedBook.getBookAuthor() + " || Book Type: "
						+ issuedBook.getBookType() + " || Issued date: " + issuedDate + " || Default Return date: "
						+ defaultReturnDate);
				position++;
			}
			System.out.println("\nWhich book number you want to return ?");
			int selectBookInput = scanner.nextInt();
			System.out.println("Enter returning date(YYYY-MM-DD): ");
			String date = scanner.next();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate returningDate = LocalDate.parse(date, formatter);
			int bookId = issuedBookList.get(selectBookInput - 1).getBookId();
			returnBook(bookId, returningDate);
		} catch (BookCatalogueException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong please try again!");
		}
	}

	@Override
	public void returnBook(int bookId, LocalDate returningDate) {
		try {
			LibraryBookReturn libraryBookReturn = libraryService.returnBook(bookId,returningDate );
			System.out.println("LAte fine : " + libraryBookReturn.getLateFine());
			if (libraryBookReturn.getLateFine() != 0) {
				System.out.println("===================================================");
				System.out.println("Book has been returned with following details");
				System.out.println("===================================================");
				System.out.println("Employee name: " + libraryBookReturn.getEmployeeName());
				System.out.println("Book type: " + libraryBookReturn.getBookType());
				System.out.println("Issued Date: " + libraryBookReturn.getIssuedDate());
				System.out.println("Return Date: " + libraryBookReturn.getReturnDate());
				System.out.println("Late Fine Rs: " + libraryBookReturn.getLateFine());
				System.out.println("===================================================");
				System.out.println("Thank you!! Please visit us again");
				System.out.println("===================================================");
			} else {
				System.out.println("\nThere is no late fee applicable and the book has been returned!!\nTHANK YOU");
			}
		} catch (BookReturningException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void showIssuedBookCatalogue() {
		try {
			int position = 1;
			List<LibraryBook> issuedBookList = libraryService.showIssuedBookCatalogue();
			System.out.println("\n=========================================");
			System.out.println("List of Issued Books for Your Account");
			System.out.println("============================================");
			for (LibraryBook issuedBook : issuedBookList) {
				LocalDate issuedDate = issuedBook.getIssuedDate();
				LocalDate defaultReturnDate = issuedBook.getReturnDate();
				System.out.print("Book " + position + ": ");
				System.out.println(issuedBook.getBookName() + " By " + issuedBook.getBookAuthor() + " || Book Type: "
						+ issuedBook.getBookType() + " || Issued date: " + issuedDate + " || Default Return date: "
						+ defaultReturnDate);
				position++;
			}
		} catch (BookCatalogueException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong please try again!");
		}
	}

}