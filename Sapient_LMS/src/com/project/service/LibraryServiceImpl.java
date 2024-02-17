package com.project.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.project.entity.LibraryBook;
import com.project.persistence.LibraryDao;
import com.project.persistence.LibraryDaoImpl;

public class LibraryServiceImpl implements LibraryService {

    private LibraryDao libraryDao = new LibraryDaoImpl();

    @Override
    public long calculateOverdueDays(int id) {
        
     LibraryBook book =    libraryDao.findBookByBookId(id);

  
     return ChronoUnit.DAYS.between(book.getIssuedDate(), book.getReturnedDate());
    }

}
