package pl.programistaodzera.LibraryApp.review;

import jakarta.persistence.*;
import pl.programistaodzera.LibraryApp.book.Book;
import pl.programistaodzera.LibraryApp.user.User;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String body;
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "review_author_id")
    private User reviewAuthor;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    public Review() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(User reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
