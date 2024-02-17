package com.project.entity;

import java.time.LocalDate;

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
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public boolean isBookIssued() {
		return isBookIssued;
	}
	public void setBookIssued(boolean isBookIssued) {
		this.isBookIssued = isBookIssued;
	}
	public LocalDate getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	
	@Override
	public String toString() {
		return "LibraryBook [bookId=" + bookId + ", bookName=" + bookName + ", bookType=" + bookType + ", bookAuthor="
				+ bookAuthor + ", isBookIssued=" + isBookIssued + ", issuedDate=" + issuedDate + ", returnDate="
				+ returnDate + "]";
	}
	
	
	

}
