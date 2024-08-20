package pl.programistaodzera.LibraryApp.review;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.programistaodzera.LibraryApp.book.BookRequestDTO;

import java.util.List;

@RestController
@RequestMapping("review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity <List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> allReviews = reviewService.getAllReviews();
        return ResponseEntity.ok(allReviews);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReviewDTO> getReviewById (@PathVariable Integer id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
    @GetMapping("all-books-reviews")
    public ResponseEntity<List<SimplifiedReviewDTO>> getAllBooksReviews (@RequestBody BookRequestDTO bookRequestDTO) {
        List<SimplifiedReviewDTO> allBooksReviews = reviewService.getAllBooksReviews(bookRequestDTO);
        return ResponseEntity.ok(allBooksReviews);
    }

    @PostMapping()
    public ResponseEntity<ReviewDTO> addReview (@RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewDTO addedReview = reviewService.addReview(reviewRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedReview);
    }

    @PutMapping("{id}")
    public ResponseEntity<ReviewDTO> updateReview (@PathVariable Integer id, @RequestBody ReviewRequestDTO reviewRequestDTO) {
        try {
            ReviewDTO updatedReview = reviewService.updateReview(id, reviewRequestDTO);
            return ResponseEntity.ok().body(updatedReview);
        } catch (EntityNotFoundException e) {
            return  ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("{id}")
    public ResponseEntity<ReviewDTO> patchReview (@PathVariable Integer id, @RequestBody ReviewRequestDTO reviewRequestDTO){
        try {
            ReviewDTO patchedReview = reviewService.patchReview(id, reviewRequestDTO);
            return ResponseEntity.ok().body(patchedReview);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReview (@PathVariable Integer id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
