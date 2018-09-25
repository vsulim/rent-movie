package rentmovie.userservice.domain;

import lombok.AllArgsConstructor;
import rentmovie.userservice.dto.PostUserDto;
import rentmovie.userservice.infrastructure.security.SecurityUtility;

import java.util.List;

@AllArgsConstructor
public class UserFacade {

    private UserRepository userRepository;
    private UserCreator userCreator;

    public User addUser(PostUserDto postUserDto) {
        User user = userCreator.create(postUserDto);

        return userRepository.insert(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
