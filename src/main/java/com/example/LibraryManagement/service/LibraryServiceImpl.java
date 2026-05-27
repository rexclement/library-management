package com.example.LibraryManagement.service;


import com.example.LibraryManagement.dao.Book;
import com.example.LibraryManagement.BookRepository.BookRepository;
import com.example.LibraryManagement.request.BookRequest;
import com.example.LibraryManagement.utils.Response;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    BookRepository bookRepository;

    @Override
    
    public Response save(BookRequest req) {

        Response response = Response.getInstance();

        Book book = new Book();
        book.setTitle(req.getTitle());
        book.setIsbn(req.getIsbn());
        book.setAuthorEmail(req.getAuthorEmail());

        bookRepository.save(book);

        response.setStatus(true);
        response.setRemarks("Book Saved Successfully");
        response.setData(book);

        return response;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Response getBookById(Long id) {

        Response response = Response.getInstance();

        Book book = bookRepository.findById(id).orElse(null);

        if(book == null){
            response.setRemarks("Book Not Found");
            return response;
        }

        response.setStatus(true);
        response.setData(book);
        return response;
    }

    @Override
    public Response deleteBook(Long id) {

        Response response = Response.getInstance();

        if(!bookRepository.existsById(id)){
            response.setRemarks("Book Not Found");
            return response;
        }

        bookRepository.deleteById(id);

        response.setStatus(true);
        response.setRemarks("Book Deleted");
        return response;
    }

    @Override
    @Transactional 
    public Response deleteBookByTitle(String title) {

        Response response = Response.getInstance();

        if(!bookRepository.existsByTitle(title)){
            response.setRemarks("Book Not Found");
            return response;
        }

        bookRepository.deleteByTitle(title);

        response.setStatus(true);
        response.setRemarks("Book Deleted Successfully");
        return response;
    }

    @Override
    public boolean isBookAlreadyPresent(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}