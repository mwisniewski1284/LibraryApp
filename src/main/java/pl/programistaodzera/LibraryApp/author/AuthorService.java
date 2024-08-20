package pl.programistaodzera.LibraryApp.author;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

   public List<AuthorDTO> getAllAuthors(){
        Iterable<Author> authors = authorRepository.findAll();
        return StreamSupport.stream(authors.spliterator(),false)
                .map(this::mapToAuthorDTO)
                .collect(Collectors.toList());
   }
   public Optional<AuthorDTO> getAuthorById(Integer id) {
        return authorRepository.findById(id)
                .map(this::mapToAuthorDTO);
   }


   public AuthorDTO addAuthor (AuthorDTO authorDTO) {
       Author author = new Author();
       author.setLastName(authorDTO.getLastName());
       author.setName(authorDTO.getName());
       return mapToAuthorDTO(authorRepository.save(author));
   }

public AuthorDTO updateAuthor (Integer id, AuthorDTO authorDTO) {
    Author author = authorRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
    author.setLastName(authorDTO.getLastName());
    author.setName(authorDTO.getName());
    return mapToAuthorDTO(authorRepository.save(author));
}

public AuthorDTO patchAuthor (Integer id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (authorDTO.getLastName()!=null) author.setLastName(authorDTO.getLastName());
        if (authorDTO.getName()!=null) author.setName(authorDTO.getName());
    Author patchedAuthor = authorRepository.save(author);
    return mapToAuthorDTO(patchedAuthor);
}

public void deleteAuthor (Integer id) {
        if (!authorRepository.existsById(id)) {
            throw  new EntityNotFoundException();
        }
        authorRepository.deleteById(id);
}

public Author addOrFindAuthor( AuthorDTO authorDTO){
      return  authorRepository.findByLastNameAndName(authorDTO.getLastName(), authorDTO.getName())
                .orElseGet(()-> {
                    Author newAuthor = new Author();
                    newAuthor.setLastName(authorDTO.getLastName());
                    newAuthor.setName(authorDTO.getName());
                    return authorRepository.save(newAuthor);
                });
}




    public AuthorDTO mapToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setLastName(author.getLastName());
        authorDTO.setName(author.getName());
        return authorDTO;
    }

    public Author mapToAuthor (AuthorDTO authorDTO) {
        Author author = new Author();
        author.setLastName(authorDTO.getLastName());
        author.setName(authorDTO.getName());
        return author;
    }

}

