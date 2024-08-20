package pl.programistaodzera.LibraryApp.user;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUser(Integer id) {
        return userRepository.findById(id)
                .map(this::mapToUserDTO);
    }

    public UserDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setDisplayName(userDTO.getDisplayName());

        return mapToUserDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        user.setDisplayName(userDTO.getDisplayName());


        return mapToUserDTO(userRepository.save(user));
    }

    public UserDTO patchUser(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (userDTO.getDisplayName() != null) user.setDisplayName(userDTO.getDisplayName());

        User patchedUser = userRepository.save(user);
        return mapToUserDTO(patchedUser);
    }
    public void deleteUser (Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        userRepository.deleteById(id);
    }


    public UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setDisplayName(user.getDisplayName());
        return userDTO;
    }
}
