package com.project.persistence;

import java.util.List;

import com.project.entity.LibraryBook;

public interface LibraryDao {
	
	List<LibraryBook> showAllBook();
	List<LibraryBook> showDataAnalyticsTypeBook();
	List<LibraryBook> showTechnologyTypeBook();
	List<LibraryBook> showManagementTypeBook();
	
	

}
