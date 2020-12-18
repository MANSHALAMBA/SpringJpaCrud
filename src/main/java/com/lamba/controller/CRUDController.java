package com.lamba.controller;




import com.lamba.model.Book;
import com.lamba.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.lamba.service.CRUDService;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;

@RestController
public class CRUDController {

   @Autowired
    BookRepository bookRepository;



    @PostMapping("/create")
    Book create(@RequestBody Book b){
         Book savedBook= bookRepository.save(new Book(b.getName(),b.getPrice(),b.getAuthors()));
         return savedBook;

    }

    @GetMapping("/read/{id}")
    Book read(@PathVariable("id") Long id){

         Book b= bookRepository.findOne(id);
         return b;

    }



    @PutMapping("/update/{id}")
    public Book update(@PathVariable("id") Long id, @RequestBody Book b) {
         Book savedBook = bookRepository.findOne(id);

        savedBook.setName(b.getName());
        savedBook.setPrice(b.getPrice());
        savedBook.setAuthors(b.getAuthors());

        return bookRepository.save(savedBook);

    }

    @GetMapping("/delete/{id}")
    void delete(@PathVariable("id") Long id){

        bookRepository.delete(id);
    }








}
