package pl.programistaodzera.LibraryApp.book;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programistaodzera.LibraryApp.author.Author;
import pl.programistaodzera.LibraryApp.author.AuthorDTO;
import pl.programistaodzera.LibraryApp.author.AuthorRepository;
import pl.programistaodzera.LibraryApp.author.AuthorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final AuthorService authorService;
    @Autowired
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorService authorService, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return StreamSupport.stream(books.spliterator(), false)
                .map(this::mapToBookDTO)
                .collect(Collectors.toList());
    }

    public Optional<BookDTO> getBookById(Integer id) {
        return bookRepository.findById(id)
                .map(this::mapToBookDTO);
    }

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = new Book();
        Author author = authorService.addOrFindAuthor(bookDTO.getAuthor());
        book.setAuthor(author);
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        Book savedBook = bookRepository.save(book);
        return mapToBookDTO(savedBook);

    }

    public BookDTO updateBook(Integer id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());

        AuthorDTO authorDTO = bookDTO.getAuthor();
        Author author;

        Author existingAuthor = authorRepository.findByLastNameAndName(authorDTO.getLastName(), authorDTO.getName()).orElse(null);

        if (existingAuthor != null) {
            author = existingAuthor;
        } else {
            author = authorService.mapToAuthor(authorDTO);

            authorRepository.save(author);
        }
        book.setAuthor(author);
        bookRepository.save(book);
        return mapToBookDTO(book);
    }

    public BookDTO patchBook(Integer id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (bookDTO.getTitle() != null) book.setTitle(bookDTO.getTitle());
        if (bookDTO.getIsbn() != null) book.setIsbn(bookDTO.getIsbn());
        if (bookDTO.getAuthor() != null) {
            AuthorDTO authorDTO = bookDTO.getAuthor();
            Author author;
            Author existingAuthor = authorRepository.findByLastNameAndName(authorDTO.getLastName(), authorDTO.getName()).orElse(null);
            if (existingAuthor != null) {
                author=existingAuthor;
            } else {
                author = authorService.mapToAuthor(authorDTO);
                authorRepository.save(author);
            }
            book.setAuthor(author);
        }
        Book patchedBook = bookRepository.save(book);
        return mapToBookDTO(patchedBook);
    }

    public void deleteBook (Integer id) {
        if (!bookRepository.existsById(id)) {
            throw  new EntityNotFoundException();
        }
        bookRepository.deleteById(id);
    }


    public BookDTO mapToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        Author author = book.getAuthor();
        AuthorDTO authorDTO = authorService.mapToAuthorDTO(author);
        bookDTO.setAuthor(authorDTO);
        return bookDTO;
    }

    public BookRequestDTO mapToBookRequestDTO (Book book) {
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        Author author = book.getAuthor();
        AuthorDTO authorDTO = authorService.mapToAuthorDTO(author);
        bookRequestDTO.setAuthor(authorDTO);
        bookRequestDTO.setTitle(book.getTitle());
        return bookRequestDTO;
    }


}

