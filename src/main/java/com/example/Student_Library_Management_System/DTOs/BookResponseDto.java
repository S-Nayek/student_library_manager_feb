package com.example.Student_Library_Management_System.DTOs;

import com.example.Student_Library_Management_System.Enums.Genre;

public class BookResponseDto {
    private String name;
    private int page;
    private Genre genre;
    //private int author_id;


    public BookResponseDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
