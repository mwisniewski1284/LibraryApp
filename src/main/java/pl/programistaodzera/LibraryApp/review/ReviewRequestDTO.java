package pl.programistaodzera.LibraryApp.review;

import pl.programistaodzera.LibraryApp.book.BookRequestDTO;
import pl.programistaodzera.LibraryApp.user.UserDTO;

public class ReviewRequestDTO {
    private UserDTO reviewAuthorDTO;
    private BookRequestDTO bookRequestDTO;
    private String body;
    private Integer rating;

    public ReviewRequestDTO() {
    }

    public UserDTO getReviewAuthorDTO() {
        return reviewAuthorDTO;
    }

    public void setReviewAuthorDTO(UserDTO reviewAuthorDTO) {
        this.reviewAuthorDTO = reviewAuthorDTO;
    }

    public BookRequestDTO getBookRequestDTO() {
        return bookRequestDTO;
    }

    public void setBookRequestDTO(BookRequestDTO bookRequestDTO) {
        this.bookRequestDTO = bookRequestDTO;
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
