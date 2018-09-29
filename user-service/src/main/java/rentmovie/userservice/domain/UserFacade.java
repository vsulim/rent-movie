package rentmovie.userservice.domain;

import lombok.AllArgsConstructor;
import rentmovie.userservice.domain.exception.UserNotFoundException;
import rentmovie.userservice.dto.PostUserDto;
import rentmovie.userservice.dto.UserDto;

import java.util.List;

@AllArgsConstructor
public class UserFacade {

    private UserRepository userRepository;
    private UserManager userManager;

    public UserDto addUser(PostUserDto postUserDto) {

        User user = userManager.create(postUserDto);

        return userRepository.insert(user)
                .convertToDto();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void addUserPunishment(String userId, String punishmentId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found."));

        User processedUser = userManager.updateUsersPunishments(user, punishmentId);

        userRepository.save(processedUser);
    }

    public UserDto findUserById(String userId) {
        return userRepository.findById(userId)
                .map(User::convertToDto)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found."));
    }

    public void removeUserPunishment(String userId, String punishmentId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found."));

        User processedUser = userManager.removeUserPunishment(user, punishmentId);

        userRepository.save(processedUser);

    }
}
