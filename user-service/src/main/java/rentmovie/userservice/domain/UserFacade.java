package rentmovie.userservice.domain;

import lombok.AllArgsConstructor;
import rentmovie.userservice.dto.PostUserDto;

import java.util.List;

@AllArgsConstructor
public class UserFacade {

    private UserRepository userRepository;
    private UserManager userManager;

    public User addUser(PostUserDto postUserDto) {

        User user = userManager.create(postUserDto);

        return userRepository.insert(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
