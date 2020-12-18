package com.lamba.test;


import com.lamba.controller.CRUDController;
import com.lamba.model.Author;
import com.lamba.model.Book;
import com.lamba.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.HashSet;
import java.util.Set;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.notNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CRUDController.class)
public class CRUDControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository repository;

    @Test
    public void testRead()
            throws Exception {

        Author author =new Author();
        Book book = new Book();

        Set<Book> books=new HashSet<Book>();
        books.add(book);

        Set<Author> auhtors=new HashSet<Author>();
        auhtors.add(author);

        author.setId(new Long(1)); author.setName("Ruskin Bond");author.setBooks(books);
        book.setId(new Long(1));book.setPrice(100.0);book.setName("Jungle Book");book.setAuthors(auhtors);





        given(repository.findOne( new Long(1)) ).willReturn(book);

        mvc.perform(get("/read/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(book.getId()))
                .andExpect(jsonPath("$.name").value(book.getName()))
                .andExpect(jsonPath("$.price").value(book.getPrice()))
                .andExpect(jsonPath("$.authors", hasSize(1)));



    }
    @Test
    public void testDelete()throws Exception
    {
        mvc.perform(get("/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testCreate() throws Exception {

        mvc.perform(post("/create")
                .content("{\"name\":\"harry potter\",\"price\":100,\"authors\":[\"jkRowls\"]}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());




    }

    @Test
    public void testUpdate() throws Exception { Author author =new Author();
        Book book = new Book();

        Set<Book> books=new HashSet<Book>();
        books.add(book);

        Set<Author> auhtors=new HashSet<Author>();
        auhtors.add(author);

        author.setId(new Long(1)); author.setName("Ruskin Bond");author.setBooks(books);
        book.setId(new Long(1));book.setPrice(100.0);book.setName("Jungle Book");book.setAuthors(auhtors);

        given(repository.findOne( new Long(1)) ).willReturn(book);

        mvc.perform(put("/update/1")
                .content("{\"name\":\"Jungle Book\",\"price\":200,\"authors\":[\"Ruskin Bond\"]}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());



    }


}
