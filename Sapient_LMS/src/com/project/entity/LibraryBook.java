package com.project.entity;

import java.time.LocalDate;

public class LibraryBook {
	private int bookId;
	private String bookName;
	private String bookType;
	private String bookAuthor;
	private Boolean isBookIssued;
	private LocalDate issuedDate;
	private LocalDate returnedDate;



	
	public LibraryBook(int bookId, String bookName, String bookType, String bookAuthor, Boolean isBookIssued,
			LocalDate issuedDate, LocalDate returnedDate) {
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookAuthor = bookAuthor;
		this.isBookIssued = isBookIssued;
		this.issuedDate = issuedDate;
		this.returnedDate = returnedDate;
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
	public Boolean getIsBookIssued() {
		return isBookIssued;
	}
	public void setIsBookIssued(Boolean isBookIssued) {
		this.isBookIssued = isBookIssued;
	}
	public LocalDate getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(LocalDate issuedDate) {
		this.issuedDate = issuedDate;
	}
	public LocalDate getReturnedDate() {
		return returnedDate;
	}
	public void setReturnedDate(LocalDate returnedDate) {
		this.returnedDate = returnedDate;
	}
	@Override
	public String toString() {
		return "LibraryBook [bookId=" + bookId + ", bookName=" + bookName + ", bookType=" + bookType + ", bookAuthor="
				+ bookAuthor + ", isBookIssued=" + isBookIssued + ", issuedDate=" + issuedDate + ", returnedDate="
				+ returnedDate + "]";
	}


	 
	
	
	
	

}
