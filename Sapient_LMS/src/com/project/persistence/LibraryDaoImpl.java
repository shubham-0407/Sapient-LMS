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
	private final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sapient_library";
	private final String DB_USER = "root";
	private final String DB_PASSWORD = "*****";

	// SQL Queries
	private final String FETCHALLBOOK = "SELECT * FROM BOOK";
	private final String FETCHBOOKBYTYPE = "SELECT * FROM BOOK WHERE BOOKTYPE=? AND ISBOOKISSUED=?";
	private final String FETCHBOOKBYID = "SELECT * FROM BOOK WHERE BOOKID=?";
	private final String UPDATEBOOKISSUEDSTATUS = "UPDATE BOOK SET ISBOOKISSUED=?,ISSUEDDATE=?, RETURNDATE=? WHERE BOOKID=?";
	 
	PreparedStatement preparedStatement;
	LibraryBook libraryBook;
	List<LibraryBook> bookList = new ArrayList<LibraryBook>();
	List<LibraryBook> bookListByType = new ArrayList<LibraryBook>();

	@Override
	public List<LibraryBook> getAllBook() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			preparedStatement = connection.prepareStatement(FETCHALLBOOK);
			ResultSet resultSet = preparedStatement.executeQuery();
			 
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookType = resultSet.getString("bookType");
				String bookAuthor = resultSet.getString("bookAuthor");
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor);
				bookList.add(libraryBook);
			}
			if (bookList.size() <= 0) {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return bookList;
	}

	@Override
	public List<LibraryBook> getBookByType(String bookType) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			preparedStatement = connection.prepareStatement(FETCHBOOKBYTYPE);
			preparedStatement.setString(1, bookType);
			preparedStatement.setBoolean(2, false);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookAuthor = resultSet.getString("bookAuthor");
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor);
				bookListByType.add(libraryBook);
			}
			if (bookListByType.size() <= 0) {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return bookListByType;
	}

	@Override
	public LibraryBook getBookById(int bookId) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			preparedStatement = connection.prepareStatement(FETCHBOOKBYID);
			preparedStatement.setInt(1, bookId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			} else {
				String bookName = resultSet.getString("bookName");
				String bookType = resultSet.getString("bookType");
				String bookAuthor = resultSet.getString("bookAuthor");
				boolean isIssued = resultSet.getBoolean("isBookIssued");
				java.sql.Date issuedDate = resultSet.getDate("issuedDate");
				LocalDate bookIssuedDate = issuedDate.toLocalDate();
				java.sql.Date returnDate = resultSet.getDate("returnDate");
				LocalDate bookReturnDate = returnDate.toLocalDate();
				libraryBook = new LibraryBook(bookId, bookName,bookType, bookAuthor, isIssued, bookIssuedDate,
						bookReturnDate);
				return libraryBook;
			}

		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return libraryBook;

	}

	@Override
	public boolean updateBookIssuedStatus(int bookId, boolean issuedStatus) {
		int issuedUpdatedRow = 0;
		LocalDate issuedDate;
		LocalDate returnDate;
		java.sql.Date bookIssuedDate;
		java.sql.Date bookReturnDate;

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {
			if (issuedStatus) {
				issuedDate = LocalDate.now();
				returnDate = LocalDate.now().plusDays(7);
				bookIssuedDate = java.sql.Date.valueOf(issuedDate);
				bookReturnDate = java.sql.Date.valueOf(returnDate);
			} else {
				issuedDate = LocalDate.now();
				returnDate = LocalDate.now();
				bookIssuedDate = java.sql.Date.valueOf(issuedDate);
				bookReturnDate = java.sql.Date.valueOf(returnDate);
			}
			preparedStatement = connection.prepareStatement(UPDATEBOOKISSUEDSTATUS);
			preparedStatement.setBoolean(1, issuedStatus);
			preparedStatement.setDate(2, bookIssuedDate);
			preparedStatement.setDate(3, bookReturnDate);
			preparedStatement.setInt(4, bookId);
			issuedUpdatedRow = preparedStatement.executeUpdate();
			if (issuedUpdatedRow <= 0) {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return true;
	}
}
