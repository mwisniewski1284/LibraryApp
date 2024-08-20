package pl.programistaodzera.LibraryApp.author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Optional<Author> findByLastNameAndName(String lastName, String name);
}
