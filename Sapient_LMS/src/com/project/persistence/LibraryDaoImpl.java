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
import com.project.exception.ExecutionErrorException;
import com.project.exception.RecordNotFoundException;

public class LibraryDaoImpl implements LibraryDao {
	final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sapient_library";
	final String DB_USER = "root";
	final String DB_PASSWORD = "Avtar@123";

	PreparedStatement preparedStatement;
	LibraryBook libraryBook;
	List<LibraryBook> bookTypeList = new ArrayList<LibraryBook>();
	List<LibraryBook> allBookList = new ArrayList<LibraryBook>();

	@Override
	public List<LibraryBook> getAllBooks() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {

			String fetchAllQuery = "SELECT * FROM BOOK";
			preparedStatement = connection.prepareStatement(fetchAllQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookType = resultSet.getString("bookType");
				String bookAuthor = resultSet.getString("bookAuthor");
				Boolean isBookIssued = resultSet.getBoolean("isBookIssued");
				LocalDate issuedDate = resultSet.getDate("issuedDate").toLocalDate();
				LocalDate returnedDate = resultSet.getDate("returnedDate").toLocalDate();
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor, isBookIssued, issuedDate,
						returnedDate);
				allBookList.add(libraryBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allBookList;
	}

	@Override
	public List<LibraryBook> getBooksByType(String bookType) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);) {

			String fetchBookTypeQuery = "SELECT * FROM BOOK WHERE BOOKTYPE=?";
			preparedStatement = connection.prepareStatement(fetchBookTypeQuery);
			preparedStatement.setString(1, bookType);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookAuthor = resultSet.getString("bookAuthor");
				Boolean isBookIssued = resultSet.getBoolean("isBookIssued");
				LocalDate issuedDate = resultSet.getDate("issuedDate").toLocalDate();
				LocalDate returnedDate = resultSet.getDate("returnedDate").toLocalDate();
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor, isBookIssued, issuedDate,
						returnedDate);
				bookTypeList.add(libraryBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookTypeList;
	}

	@Override
	public LibraryBook findBookByBookId(int id) {
		LibraryBook libraryBook = null;

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM BOOK WHERE bookId = ?")) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookType = resultSet.getString("bookType");
				String bookAuthor = resultSet.getString("bookAuthor");
				Boolean isBookIssued = resultSet.getBoolean("isBookIssued");
				LocalDate issuedDate = resultSet.getDate("issuedDate") != null
						? resultSet.getDate("issuedDate").toLocalDate()
						: null;

				LocalDate returnedDate = resultSet.getDate("returnedDate") != null
						? resultSet.getDate("returnedDate").toLocalDate()
						: null;

				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor, isBookIssued, issuedDate,
						returnedDate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return libraryBook;
	}

	@Override
	public int updateBookReturnStatus(int id, LocalDate returnDate) {
		int updateCount = 0;

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("Update Book Set returnedDate=? Where bookId=?")) {

			// Business Logic to be done in service
			java.sql.Date sqlReturnDate = (returnDate != null) ? java.sql.Date.valueOf(returnDate) : null;

			preparedStatement.setDate(1, sqlReturnDate);
			preparedStatement.setInt(2, id);

			updateCount = preparedStatement.executeUpdate();

			if (updateCount > 0)
				return updateCount;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateBookIssueStatus(int id, LocalDate issueDate) {
		int updateCount = 0;

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE Book SET issuedDate=?, isBookIssued=true WHERE bookId=?")) {

			// Business Logic to be done in service
			java.sql.Date sqlIssueDate = (issueDate != null) ? java.sql.Date.valueOf(issueDate) : null;

			preparedStatement.setDate(1, sqlIssueDate);
			preparedStatement.setInt(2, id);

			updateCount = preparedStatement.executeUpdate();

			if (updateCount > 0)
				return updateCount;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

}
