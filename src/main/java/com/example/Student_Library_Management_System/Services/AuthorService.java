package com.example.Student_Library_Management_System.Services;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDTO;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDTO authorEntryDTO){


        Author author =new Author();
        author.setName(authorEntryDTO.getName());
        author.setAge(authorEntryDTO.getAge());
        author.setCountry(authorEntryDTO.getCountry());
        author.setRating(authorEntryDTO.getRating());

        authorRepository.save(author);
        return "Author created Successfully";
    }

    public AuthorResponseDto getAuthor(int author_id) {
        Author author=authorRepository.findById(author_id).get();
        List<Book> bookWitten =author.getBooksWritten();
        List<BookResponseDto> bookList =new ArrayList<>();
        for(Book b: bookWitten){
            BookResponseDto bookResponseDto=new BookResponseDto();
            bookResponseDto.setName(b.getName());
            bookResponseDto.setPage(b.getPage());
            bookResponseDto.setGenre(b.getGenre());

            bookList.add(bookResponseDto);
        }
        AuthorResponseDto authorResponseDto=new AuthorResponseDto();
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());
        authorResponseDto.setCountry(author.getCountry());
        authorResponseDto.setListOfBook(bookList);

            return authorResponseDto;
    }
}
