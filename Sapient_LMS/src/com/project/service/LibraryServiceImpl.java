package com.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.project.entity.EmployeeBookCatalogue;
import com.project.entity.LibraryBook;
import com.project.exception.BookCatalogueException;
import com.project.exception.RecordNotFoundException;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;

public class LibraryServiceImpl implements LibraryService {

	LibraryDao libraryDao = new LibraryDaoImpl();
	EmployeeBookCatalogue employeeBookCatalogue;
	List<LibraryBook> bookList = new ArrayList<LibraryBook>();
	List<LibraryBook> bookListByType = new ArrayList<LibraryBook>();
	LibraryBook libraryBook;
	
	// Creating first time book catalogue with employee name
	@Override
	public boolean createBookCatalogue(String name) throws BookCatalogueException {
		if (name.equals(" ")) {
			throw new BookCatalogueException("NAME is missing\nPlease provide your name!!\n\nTHANK YOU");
		} else {
			employeeBookCatalogue = new EmployeeBookCatalogue(name);
			boolean isCreated = employeeBookCatalogue.isCatalogueCreated();
			if (isCreated) {
				return true;
			} else {
				throw new BookCatalogueException(
						"An error occured while creating book catalogue!!\nPlease try again Later\nTHANK YOU!!\"");
			}
		}
	}

	// Adding book to the employee's book catalogue
	@Override
	public boolean issueBook(int bookId) throws BookCatalogueException {
		libraryBook = libraryDao.showBookById(bookId);
		if(libraryBook == null) {
			throw new BookCatalogueException(
					"An error occured while issuing book!!\nPlease try again Later\nTHANK YOU!!");
		}else {
			LocalDate issuedDate = LocalDate.now();
			boolean isIssued = employeeBookCatalogue.setIssuedBooks(libraryBook);
			boolean isUpdated = libraryDao.updateBookIssuedDate(bookId, issuedDate);
			if (isIssued && isUpdated) {
				return true;
			} else {
				throw new BookCatalogueException(
						"An error occured while issuing book by catalogue!!\nPlease try again Later\nTHANK YOU!!");
			}
		}
	}

	// Returning list of all issued book of the employee
	@Override
	public EmployeeBookCatalogue showIssuedBookCatalogue() throws BookCatalogueException {
		if (!employeeBookCatalogue.getEmployeeName().equals("")) {
			return employeeBookCatalogue;
		} else {
			throw new BookCatalogueException(
					"An Error Occured While Fetching Your Catalogue Details!!\n\nPlease try again later\n\n\nTHANK YOU");
		}
	}

	// Method to fetch all book list in the library
	@Override
	public List<LibraryBook> fetchAllBook() throws RecordNotFoundException {
		bookList = libraryDao.showAllBook();
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
		bookListByType = libraryDao.showBookByType(bookType);
		if (bookListByType == null) {
			throw new RecordNotFoundException(bookType.toUpperCase()
					+ " Type Books Not Available in the Library!!\nPlease Visit Us Again\\nTHANK YOU!!");
		} else {
			return bookListByType;
		}
	}

	// Method to fetch all book list in the library by bookType
	@Override
	public LibraryBook fetchBookById(int bookId) throws RecordNotFoundException {
		libraryBook = libraryDao.showBookById(bookId);
		if (libraryBook == null) {
			throw new RecordNotFoundException( 
					"No Book Available with book id "+bookId+" in the Library!!\nPlease Visit Us Again\\nTHANK YOU!!");
		} else {
			return libraryBook;
		}
	}

}
