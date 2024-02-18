package com.project.entity;

import java.time.LocalDate;
import java.util.Objects;

public class LibraryBook {
	private int bookId;
	private String bookName;
	private String bookType;
	private String bookAuthor;
	private boolean isBookIssued;
	private LocalDate issuedDate;
	private LocalDate returnDate;

	public LibraryBook(int bookId, String bookName, String bookType, String bookAuthor) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookAuthor = bookAuthor;
	}

	public LibraryBook(int bookId, String bookName, String bookType, String bookAuthor, boolean isBookIssued,
			LocalDate issuedDate, LocalDate returnDate) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookAuthor = bookAuthor;
		this.isBookIssued = isBookIssued;
		this.issuedDate = issuedDate;
		this.returnDate = returnDate;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookType() {
		return bookType;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public boolean isBookIssued() {
		return isBookIssued;
	}

	public LocalDate getIssuedDate() {
		return issuedDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookAuthor, bookId, bookName, bookType, isBookIssued, issuedDate, returnDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryBook other = (LibraryBook) obj;
		return Objects.equals(bookAuthor, other.bookAuthor) && bookId == other.bookId
				&& Objects.equals(bookName, other.bookName) && Objects.equals(bookType, other.bookType)
				&& isBookIssued == other.isBookIssued && Objects.equals(issuedDate, other.issuedDate)
				&& Objects.equals(returnDate, other.returnDate);
	}

}
