package pl.programistaodzera.LibraryApp.book;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("books")
public class BookController  {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = bookService.getAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        BookDTO addedBook = bookService.addBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }
    @PutMapping("{id}")
    public ResponseEntity<BookDTO> updateBook (@PathVariable Integer id, @RequestBody BookDTO bookDTO) {
        try {
            BookDTO updatedBook = bookService.updateBook(id, bookDTO);
            return ResponseEntity.ok().body(updatedBook);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<BookDTO> patchBook (@PathVariable Integer id, @RequestBody BookDTO bookDTO){
        try {
            BookDTO patchedBook = bookService.patchBook(id, bookDTO);
            return  ResponseEntity.ok().body(patchedBook);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook (@PathVariable Integer id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
