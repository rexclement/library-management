package com.example.LibraryManagement.service;

import com.example.LibraryManagement.dao.Book;
import com.example.LibraryManagement.request.BookRequest;
import com.example.LibraryManagement.utils.Response;

import java.util.List;

public interface LibraryService {

    Response save(BookRequest book);

    List<Book> getAllBooks();

    Response getBookById(Long id);

    Response deleteBook(Long id);

    Response deleteBookByTitle(String title);

    boolean isBookAlreadyPresent(String isbn);
}


