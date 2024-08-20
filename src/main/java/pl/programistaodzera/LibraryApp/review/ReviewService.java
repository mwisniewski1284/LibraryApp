package pl.programistaodzera.LibraryApp.review;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programistaodzera.LibraryApp.book.*;
import pl.programistaodzera.LibraryApp.user.User;
import pl.programistaodzera.LibraryApp.user.UserDTO;
import pl.programistaodzera.LibraryApp.user.UserRepository;
import pl.programistaodzera.LibraryApp.user.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    private final BookService bookService;
    @Autowired
    private final BookRepository bookRepository;


    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, UserService userService, BookService bookService, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return StreamSupport.stream(reviews.spliterator(), false)
                .map(this::mapToReviewDTO)
                .collect(Collectors.toList());

    }

    public Optional<ReviewDTO> getReviewById(Integer id) {
        return reviewRepository.findById(id)
                .map(this::mapToReviewDTO);
    }

    public List<SimplifiedReviewDTO> getAllBooksReviews (BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findBookByTitle(bookRequestDTO.getTitle());
        if (book == null) {
            throw new EntityNotFoundException("Książki nie znaleziono");
        }
        List<Review> byBook = reviewRepository.findByBook(book);
        return byBook.stream()
                .map(review -> {
                    UserDTO userDTO = userService.mapToUserDTO(review.getReviewAuthor());
                    return new SimplifiedReviewDTO(
                            userDTO.getDisplayName(), review.getBody(), review.getRating()
                    );
                })
                .collect(Collectors.toList());
    }


    public ReviewDTO addReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = new Review();
        String reviewAuthorDisplayName = reviewRequestDTO.getReviewAuthorDTO().getDisplayName();
        User reviewAuthorUser = userRepository.findUserByDisplayName(reviewAuthorDisplayName);

        try {
            review.setReviewAuthor(reviewAuthorUser);
        } catch (EntityNotFoundException e) {
            System.out.println("Nie znaleziono użytkownika");
        }
        review.setBody(reviewRequestDTO.getBody());

        String title = reviewRequestDTO.getBookRequestDTO().getTitle();
        Book book = bookRepository.findBookByTitle(title);

        review.setBook(book);
        review.setRating(reviewRequestDTO.getRating());
        Review savedReview = reviewRepository.save(review);
        return mapToReviewDTO(savedReview);
    }
    public ReviewDTO updateReview (Integer id, ReviewRequestDTO reviewRequestDTO) {
        Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        review.setBody(reviewRequestDTO.getBody());
        review.setRating(reviewRequestDTO.getRating());
        reviewRepository.save(review);
        return mapToReviewDTO(review);
    }
    public ReviewDTO patchReview (Integer id, ReviewRequestDTO reviewRequestDTO) {
        Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (reviewRequestDTO.getBody()!= null) review.setBody(reviewRequestDTO.getBody());
        if (reviewRequestDTO.getRating()!=null) review.setRating(reviewRequestDTO.getRating());
        Review patchedReview = reviewRepository.save(review);
        return mapToReviewDTO(patchedReview);
    }

    public void deleteReview (Integer id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        reviewRepository.deleteById(id);
    }


    private ReviewDTO mapToReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();

        UserDTO reviewAuthorDTO = userService.mapToUserDTO(review.getReviewAuthor());
        reviewDTO.setReviewAuthorDTO(reviewAuthorDTO);

        BookDTO bookDTO = bookService.mapToBookDTO(review.getBook());
        reviewDTO.setBookDTO(bookDTO);

        reviewDTO.setBody(review.getBody());
        reviewDTO.setRating(review.getRating());
        return reviewDTO;
    }
}