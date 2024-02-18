package com.project.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import com.project.entity.LibraryBook;
import com.project.entity.LibraryBookLateFine;
import com.project.entity.LibraryBookReturn;
import com.project.entity.LibraryBookType;

import java.util.ArrayList;
import java.util.List;
import com.project.entity.EmployeeBookCatalogue;
import com.project.exception.BookCatalogueException;
import com.project.exception.BookReturningException;
import com.project.exception.RecordNotFoundException;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;

public class LibraryServiceImpl implements LibraryService {

	private LibraryDao libraryDao = new LibraryDaoImpl();
	private LibraryBookLateFine libraryBookLateFine = new LibraryBookLateFine();
	private LibraryBookType libraryBookType = new LibraryBookType();

	EmployeeBookCatalogue employeeBookCatalogue;
	boolean isBookIssued = false;
	List<LibraryBook> bookList = new ArrayList<LibraryBook>();
	List<LibraryBook> bookListByType = new ArrayList<LibraryBook>();

	// Creating first time book catalogue with employee name
	@Override
	public EmployeeBookCatalogue createBookCatalogue(String name) throws BookCatalogueException {
		if (name.isEmpty() || name.isBlank()) {
			throw new BookCatalogueException("NAME is missing\nPlease provide your name!!\n\nTHANK YOU");
		} else {
			employeeBookCatalogue = new EmployeeBookCatalogue(name);
			boolean isCreated = employeeBookCatalogue.isCatalogueCreated();
			if (isCreated) {
				return employeeBookCatalogue;
			} else {
				throw new BookCatalogueException(
						"An error occured while creating book catalogue!!\nPlease try again Later\nTHANK YOU!!\"");
			}
		}
	}

	// Adding book to the employee's book catalogue
	@Override
	public boolean issueBook(int bookId) throws BookCatalogueException {
		isBookIssued = true;
		boolean isUpdated = libraryDao.updateBookIssuedStatus(bookId, isBookIssued);
		if (!isUpdated) {
			throw new BookCatalogueException(
					"An Internal Server Error Occurred!!\nPlease try again Later\nTHANK YOU!!");
		} else {
			LibraryBook libraryBook = libraryDao.getBookById(bookId);
			boolean isIssued = employeeBookCatalogue.setIssuedBooks(libraryBook);
			if (isIssued) {
				return true;
			} else {
				isBookIssued = false;
				libraryDao.updateBookIssuedStatus(bookId, isBookIssued);
				throw new BookCatalogueException(
						"An error occured while issuing book!!\nPlease try again Later\nTHANK YOU!!");
			}
		}
	}

	// Returning list of all issued book of the employee
	@Override
	public List<LibraryBook> showIssuedBookCatalogue() throws BookCatalogueException {
		List<LibraryBook> issuedbooks;
		if (employeeBookCatalogue != null) {
			if (!employeeBookCatalogue.getIssuedBooks().isEmpty()) {
				issuedbooks = employeeBookCatalogue.getIssuedBooks();
				return issuedbooks;
			} else {
				throw new BookCatalogueException(
						"No Issued Books Found For Your Account!!\n\nTHANK YOU");
			}
		} else {
			throw new BookCatalogueException("An Error Occured While Fetching Your Catalogue Details!!\nPlease try again later\n\nTHANK YOU");
		}

	}

	// Calculating number of over due days
	@Override
	public long calculateOverdueDays(LocalDate defaultReturnDate, LocalDate userReturningDate) {
		long overDueDays = ChronoUnit.DAYS.between(defaultReturnDate, userReturningDate);
		if (overDueDays  < 0) {
			return 0;
		} else {
			return overDueDays;
		}
	}

	// Calculating late fine while returning book
	@Override
	public double calculateLateFine(String bookType, long overDays) throws BookReturningException {
		double totalLateFine = 0.0;
		if (bookType.equalsIgnoreCase(libraryBookType.getDataAnalyticsBookType())) {
			totalLateFine = libraryBookLateFine.getDataAnalyticsLateFine() * overDays;
			return totalLateFine;
		} else if (bookType.equalsIgnoreCase(libraryBookType.getManagementBookType())) {
			totalLateFine = libraryBookLateFine.getManagementTypeLateFine() * overDays;
			return totalLateFine;
		} else if (bookType.equalsIgnoreCase(libraryBookType.getTechnologyBookType())) {
			totalLateFine = libraryBookLateFine.getTechnologyTypeLateFine() * overDays;
			return totalLateFine;
		} else {
			throw new BookReturningException("Error in Calculating Late Fine\nPlease try again!!");
		}
	}

	// Method for returning book
	@Override
	public LibraryBookReturn returnBook(int bookId, LocalDate userReturningDate) throws BookReturningException {
		LibraryBook libraryBook = libraryDao.getBookById(bookId);

		if (userReturningDate.isBefore(libraryBook.getIssuedDate())) {
			throw new BookReturningException("Return date cannot be before issuing date");
		}

		if (employeeBookCatalogue != null) {
			isBookIssued = false;
			boolean isReturnUpdated = libraryDao.updateBookIssuedStatus(bookId, isBookIssued);
			if (isReturnUpdated) {
				boolean isReturned = employeeBookCatalogue.returnBook(libraryBook);
				if (isReturned) {
					String name = employeeBookCatalogue.getEmployeeName();
					LocalDate bookIssuedDate = libraryBook.getIssuedDate();
					LocalDate defaultReturnDate = libraryBook.getReturnDate();
					long overDays = calculateOverdueDays(defaultReturnDate, userReturningDate);
					String bookType = libraryBook.getBookType();
					double totalFine = calculateLateFine(bookType, overDays);
					LibraryBookReturn libraryBookReturn = new LibraryBookReturn(name, bookType, bookIssuedDate,
							userReturningDate, totalFine);
					return libraryBookReturn;
				} else {
					throw new BookReturningException("No isssued books found for your account!!\n\nTHANK YOU");
				}
			}else {
				throw new BookReturningException("Internal Server Error Occured!!\nPlease try again later!!\n\nTHANK YOU");
			}

		} else {
			throw new BookReturningException("Error occured while returning your book\nPlease try again later!!");
		}

	}

	// Method to fetch all book list in the library
	@Override
	public List<LibraryBook> fetchAllBook() throws RecordNotFoundException {
		bookList = libraryDao.getAllBook();
		if (bookList == null) {
			throw new RecordNotFoundException(
					"No Books Available in the Library!!\nPlease Visit Us Again\\nTHANK YOU!!");
		} else {
			return bookList;
		}
	}

	// Method to fetch all book list in the library by bookType
	@Override
	public List<LibraryBook> fetchBookByType(String bookType) throws RecordNotFoundException {
		bookListByType = libraryDao.getBookByType(bookType);
		if (bookListByType == null) {
			throw new RecordNotFoundException(bookType.toUpperCase()
					+ " Type Books Not Available in the Library!!\nPlease Visit Us Again\n\nTHANK YOU!!");
		} else {
			return bookListByType;
		}
	}

	// Method to fetch all book list in the library by bookType
	@Override
	public LibraryBook fetchBookById(int bookId) throws RecordNotFoundException {
		LibraryBook libraryBook;
		libraryBook = libraryDao.getBookById(bookId);
		if (libraryBook == null) {
			throw new RecordNotFoundException("No Book Available with book id " + bookId
					+ " in the Library!!\nPlease Visit Us Again\\nTHANK YOU!!");
		} else {
			return libraryBook;
		}
	}

}
