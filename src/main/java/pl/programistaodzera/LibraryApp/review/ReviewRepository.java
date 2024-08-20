package pl.programistaodzera.LibraryApp.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.programistaodzera.LibraryApp.book.Book;
import pl.programistaodzera.LibraryApp.book.BookRequestDTO;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByBookTitle(BookRequestDTO bookRequestDTO);

    List<Review> findByBook (Book book);

}
