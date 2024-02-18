package com.project.presentation;

import java.time.LocalDate;

public interface LibraryPresentation {
	void loginMenu();

	void libraryOperationMenu();

	void libraryOperationPerformMenu(int choice);

	void showAllBookListMenu();

	void showBookTypeMenu();

	void showBookListByType(String bookType);

	void returnBookCatalogue();

	void showIssuedBookCatalogue();

	void returnBook(int bookId, LocalDate returningDate);

	void issueBook(int bookId);
}
