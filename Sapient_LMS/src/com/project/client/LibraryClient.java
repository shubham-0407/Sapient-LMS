package com.project.client;

import java.util.Scanner;

import com.project.presentation.LibraryPresentation;
import com.project.presentation.LibraryPresentationImpl;

public class LibraryClient {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		LibraryPresentation libraryPresentation = new LibraryPresentationImpl();
		libraryPresentation.loginMenu();
		while (true) {
			libraryPresentation.libraryOperationMenu();
			System.out.print("\nEnter Choice : ");
			int choice = scanner.nextInt();
			libraryPresentation.libraryOperationPerformMenu(choice);
		}
	}
}
