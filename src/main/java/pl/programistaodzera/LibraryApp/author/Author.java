package pl.programistaodzera.LibraryApp.author;

import jakarta.persistence.*;
import pl.programistaodzera.LibraryApp.book.Book;

import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastName;
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
