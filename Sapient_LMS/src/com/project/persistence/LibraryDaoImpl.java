package com.project.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.project.entity.LibraryBook;
import com.project.exception.ExecutionErrorException;
import com.project.exception.RecordNotFoundException;

public class LibraryDaoImpl implements LibraryDao{
	final String DB_URL = "jdbc:mysql://127.0.0.1:3306/sapient_library";
	final String DB_USER = "root";
	final String DB_PASSWORD = "shubh@0407";
	
	PreparedStatement preparedStatement;
	LibraryBook libraryBook;
	List<LibraryBook> bookTypeList = new ArrayList<LibraryBook>();
	List<LibraryBook> allBookList = new ArrayList<LibraryBook>();
	 
	@Override
	public List<LibraryBook> showAllBook() throws ExecutionErrorException, RecordNotFoundException {
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);)
		{
			if(connection == null) {
				throw new ExecutionErrorException("Error in establish connection\nTry again!!");
			}
			String fetchAllQuery = "SELECT * FROM BOOK";
			preparedStatement = connection.prepareStatement(fetchAllQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				throw new RecordNotFoundException("No book is available in Library!!");
			}
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookType = resultSet.getString("bookType");
				String bookAuthor = resultSet.getString("bookAuthor");
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor);
				allBookList.add(libraryBook);
			}
		} catch (SQLException e) {
			throw new ExecutionErrorException("Exception occured!!\nError in executing SQL Queries");
		}
		return allBookList;
	}

	 
	@Override
	public List<LibraryBook> showBookByType(String bookType) throws  ExecutionErrorException, RecordNotFoundException{
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);)
		{
			if(connection == null) {
				throw new ExecutionErrorException("Error in establish connection\nTry again!!");
			}
			String fetchBookDataAnalyticsQuery = "SELECT * FROM BOOK WHERE BOOKTYPE=?";
			preparedStatement = connection.prepareStatement(fetchBookDataAnalyticsQuery);
			preparedStatement.setString(1, bookType);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				throw new RecordNotFoundException(bookType+" Book Type is not available!!");
			}
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookAuthor = resultSet.getString("bookAuthor");
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor);
				bookTypeList.add(libraryBook);
			}
		} catch (SQLException e) {
			throw new ExecutionErrorException("Exception occured!!\nError in executing SQL Queries");
		}
		return bookTypeList;
	}
}
