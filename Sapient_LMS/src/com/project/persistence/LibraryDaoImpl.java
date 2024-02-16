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
	final String DB_PASSWORD = "Avtar@123";
	
	PreparedStatement preparedStatement;
	LibraryBook libraryBook;
	List<LibraryBook> bookTypeList = new ArrayList<LibraryBook>();
	List<LibraryBook> allBookList = new ArrayList<LibraryBook>();
	 
	@Override
	public List<LibraryBook> showAllBook() {
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);)
		{
			
			String fetchAllQuery = "SELECT * FROM BOOK";
			preparedStatement = connection.prepareStatement(fetchAllQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				return null;
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
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
			// new ExecutionErrorException();
		}
		return allBookList;
	}

	 
	@Override
	public List<LibraryBook> showBookByType(String bookType){
		try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);)
		{
			
			String fetchBookDataAnalyticsQuery = "SELECT * FROM BOOK WHERE BOOKTYPE=?";
			preparedStatement = connection.prepareStatement(fetchBookDataAnalyticsQuery);
			preparedStatement.setString(1, bookType);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				return null;
			}
			while (resultSet.next()) {
				int bookId = resultSet.getInt("bookId");
				String bookName = resultSet.getString("bookName");
				String bookAuthor = resultSet.getString("bookAuthor");
				libraryBook = new LibraryBook(bookId, bookName, bookType, bookAuthor);
				bookTypeList.add(libraryBook);
			}
		} catch (SQLException e) {
			System.out.println("Error in establish connection !! Please Check Your Credentials!!");
		}
		return bookTypeList;
	}
}
