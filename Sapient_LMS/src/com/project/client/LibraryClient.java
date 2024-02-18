package com.project.client;

import java.util.List;
import java.util.Scanner;

import com.project.entity.LibraryBook;
import com.project.exception.ExecutionErrorException;
import com.project.exception.RecordNotFoundException;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;
import com.project.presentation.LibraryPresentation;
import com.project.presentation.LibraryPresentationImpl;

public class LibraryClient {

	public static void main(String[] args) {
		LibraryDao libraryDao = new LibraryDaoImpl();

		Scanner scanner = new Scanner(System.in);

		LibraryPresentation libraryPresentation = new LibraryPresentationImpl();

		while (true) {
			libraryPresentation.showMenu();
			System.out.println("Enter Choice : ");
			int choice = scanner.nextInt();
			libraryPresentation.performMenu(choice);
		}
	}
}
