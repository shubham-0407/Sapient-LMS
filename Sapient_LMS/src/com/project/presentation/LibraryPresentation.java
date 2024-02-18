package com.project.presentation;

import java.time.LocalDate;

public interface LibraryPresentation {
	public void loginMenu();
	public void libraryOperationMenu();
	public void libraryOperationPerformMenu(int choice);
	public void showAllBookListMenu();
	public void showBookTypeMenu();
	public void showBookListByType(String bookType);
	public void returnBookCatalogue();
	public void showIssuedBookCatalogue();
	void returnBook(int bookId, LocalDate returningDate);
	void issueBook(int bookId);
}
