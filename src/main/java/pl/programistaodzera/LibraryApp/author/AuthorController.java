package pl.programistaodzera.LibraryApp.author;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> gellAllAuthors() {
        List<AuthorDTO> allAuthors = authorService.getAllAuthors();
        return ResponseEntity.ok(allAuthors);
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getAuthorByID(@PathVariable Integer id) {
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping()
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = authorService.addAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @PutMapping("{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Integer id, @RequestBody AuthorDTO authorDTO) {
        try {
            AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
            return ResponseEntity.ok().body(updatedAuthor);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("{id}")
    public ResponseEntity<AuthorDTO> patchAuthor (@PathVariable Integer id, @RequestBody AuthorDTO authorDTO) {
        try {
            AuthorDTO patchedAuthor = authorService.patchAuthor(id, authorDTO);
            return ResponseEntity.ok().body(patchedAuthor);
        } catch (EntityNotFoundException e ) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAuthor (@PathVariable Integer id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

