package com.project.entity;

public class LibraryBook {
	private int bookId;
	private String bookName;
	private String bookType;
	private String bookAuthor;
	 
	public LibraryBook(int bookId, String bookName, String bookType, String bookAuthor) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookType = bookType;
		this.bookAuthor = bookAuthor;
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
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookType=" + bookType + ", bookAuthor="
				+ bookAuthor + "]";
	}
	
	
	

}
