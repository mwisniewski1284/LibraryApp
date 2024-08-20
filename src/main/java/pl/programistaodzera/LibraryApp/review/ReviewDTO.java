package pl.programistaodzera.LibraryApp.review;

import pl.programistaodzera.LibraryApp.book.Book;
import pl.programistaodzera.LibraryApp.book.BookDTO;
import pl.programistaodzera.LibraryApp.user.User;
import pl.programistaodzera.LibraryApp.user.UserDTO;

public class ReviewDTO {
    private UserDTO reviewAuthorDTO;
    private BookDTO bookDTO;
    private String body;
    private Integer rating;

    public ReviewDTO() {
    }

    public ReviewDTO(UserDTO reviewAuthorDTO, String body, Integer rating) {
        this.reviewAuthorDTO = reviewAuthorDTO;
        this.body = body;
        this.rating = rating;
    }

    public UserDTO getReviewAuthorDTO() {
        return reviewAuthorDTO;
    }

    public void setReviewAuthorDTO(UserDTO reviewAuthorDTO) {
        this.reviewAuthorDTO = reviewAuthorDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
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
}
