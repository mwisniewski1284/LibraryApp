package pl.programistaodzera.LibraryApp.book;

import pl.programistaodzera.LibraryApp.author.AuthorDTO;

public class BookRequestDTO {
    private String title;
    private AuthorDTO author;

    public BookRequestDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
