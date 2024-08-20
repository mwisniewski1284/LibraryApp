package pl.programistaodzera.LibraryApp.book;

import pl.programistaodzera.LibraryApp.author.Author;
import pl.programistaodzera.LibraryApp.author.AuthorDTO;
import pl.programistaodzera.LibraryApp.review.ReviewDTO;

import java.util.List;

public class BookDTO {
    private String title;
    private String isbn;
    private AuthorDTO author;
    private List<ReviewDTO> reviewDTOList;

    public BookDTO () {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }
}
