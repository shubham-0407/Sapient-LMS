package com.project.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.project.entity.LibraryBook;

public class LibraryDaoImpl implements LibraryDao {

	// Connection Details
	final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sapient_library";
	final String DB_USER = "root";
	final String DB_PASSWORD = "shubh@0407";

	// SQL Queries
	private final String FETCHALLBOOK = "SELECT * FROM BOOK";
	private final String FETCHBOOKBYTYPE = "SELECT * FROM BOOK WHERE BOOKTYPE=? AND ISBOOKISSUED=?";
	private final String FETCHBOOKBYID = "SELECT * FROM BOOK WHERE BOOKID=?";
	private final String UPDATEBOOKISSUEDDATE = "UPDATE BOOK SET ISBOOKISSUED=?,ISSUEDDATE=? WHERE BOOKID=?";
	private final String UPDATEBOOKRETURNDATE = "UPDATE BOOK SET ISBOOKISSUED=?,RETURNDATE=? WHERE BOOKID=?";
	
	PreparedStatement preparedStatement;
	LibraryBook libraryBook;
	List<LibraryBook> bookList = new ArrayList<LibraryBook>();
	List<LibraryBook> bookListByType = new ArrayList<LibraryBook>();
	@Override
	public List<LibraryBook> showAllBook() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			preparedStatement = connection.prepareStatement(FETCHALLBOOK);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookType = resultSet.getString("bookType");
				String bookAuthor = resultSet.getString("bookAuthor");
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor);
				bookList.add(libraryBook);
			}
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return bookList;
	}

	@Override
	public List<LibraryBook> showBookByType(String bookType) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			preparedStatement = connection.prepareStatement(FETCHBOOKBYTYPE);
			preparedStatement.setString(1, bookType);
			preparedStatement.setBoolean(2, false);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookAuthor = resultSet.getString("bookAuthor");
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor);
				bookListByType.add(libraryBook);
			}
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return bookListByType;
	}

	@Override
	public LibraryBook showBookById(int bookId) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			preparedStatement = connection.prepareStatement(FETCHBOOKBYID);
			preparedStatement.setInt(1, bookId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}else {
				String bookName = resultSet.getString("bookName");
				String bookType = resultSet.getString("bookType");
				String bookAuthor = resultSet.getString("bookAuthor");
				boolean isIssued = resultSet.getBoolean("isBookIssued");
				java.sql.Date issuedDate = resultSet.getDate("issuedDate");
				LocalDate bookIssuedDate = issuedDate.toLocalDate();
				java.sql.Date returnDate = resultSet.getDate("returnDate");
				LocalDate bookReturnDate = returnDate.toLocalDate();
				libraryBook = new LibraryBook(bookId, bookName, bookAuthor, bookType, isIssued, bookIssuedDate, bookReturnDate);
				return libraryBook;
			}
			 
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return libraryBook;
			
	}

	@Override
	public boolean updateBookIssuedDate(int bookId, LocalDate issuedDate) {
		int issuedUpdatedRow = 0 ;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			preparedStatement = connection.prepareStatement(UPDATEBOOKISSUEDDATE);
			// Convert LocalDate to java.sql.Date
            java.sql.Date bookIssuedDate = java.sql.Date.valueOf(issuedDate);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setDate(2, bookIssuedDate);
			preparedStatement.setInt(3, bookId);
			issuedUpdatedRow = preparedStatement.executeUpdate();
			if(issuedUpdatedRow <= 0) {
				return false;
			} 
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return true;
	}


	@Override
	public boolean updateBookReturnDate(int bookId, LocalDate returnDate) {
		int returnUpdatedRow = 0 ;
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			preparedStatement = connection.prepareStatement(UPDATEBOOKRETURNDATE);
			// Convert LocalDate to java.sql.Date
            java.sql.Date bookReturnDate = java.sql.Date.valueOf(returnDate);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setDate(2, bookReturnDate);
			preparedStatement.setInt(3, bookId);
			returnUpdatedRow = preparedStatement.executeUpdate();
			if(returnUpdatedRow <= 0) {
				return false;
			} 
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return true;
	}
}
