package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.BookReqDTO;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository;
    public String addBook(BookReqDTO bookReqDTO){
        int authorId= bookReqDTO.getAuthorId();
        Author author =authorRepository.findById(authorId).get();
        Book book=new Book();
        book.setGenre(bookReqDTO.getGenre());
        book.setName(bookReqDTO.getName());
        book.setPage(bookReqDTO.getPage());
        book.setIssued(false);
        book.setAuthor(author);

        List<Book> currentBookWritten =author.getBooksWritten();
        currentBookWritten.add(book);

        authorRepository.save(author);
        return "book added successfully";

    }
}
