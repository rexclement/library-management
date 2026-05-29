package com.example.LibraryManagement.controller;


import com.example.LibraryManagement.dao.Book;
import com.example.LibraryManagement.request.BookRequest;
import com.example.LibraryManagement.service.LibraryService;
import com.example.LibraryManagement.utils.Response;
import com.example.LibraryManagement.utils.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    // CREATE BOOK
    @PostMapping("/addBook")
    public Response createBook(@RequestBody BookRequest book){

        Response response = Response.getInstance();

        if(book.getIsbn()!=null){
            book.setIsbn(book.getIsbn().replace("-", StringUtils.EMPTY));
        }

        if(!Validator.isValidISBN(book.getIsbn())){
            response.setRemarks("Invalid ISBN");
            return response;
        }

        if(libraryService.isBookAlreadyPresent(book.getIsbn())){
            response.setRemarks("Book already exists");
            return response;
        }

        if(!Validator.isValidEmail(book.getAuthorEmail())){
            response.setRemarks("Invalid Email");
            return response;
        }

        return libraryService.save(book);
    }

    // GET ALL BOOKS
    @GetMapping("GetAllBooks")
    public List<Book> getBooks(){
        return libraryService.getAllBooks();
    }

    // GET BOOK BY ID
    @GetMapping("/{id}")
    public Response getBook(@PathVariable Long id){
        return libraryService.getBookById(id);
    }

    // DELETE BY ID
    @DeleteMapping("/{id}")
    public Response deleteBook(@PathVariable Long id){
        return libraryService.deleteBook(id);
    }

    // DELETE BY TITLE
    @DeleteMapping
    public Response deleteBookByTitle(@RequestParam String title){
        return libraryService.deleteBookByTitle(title);
    }
}
