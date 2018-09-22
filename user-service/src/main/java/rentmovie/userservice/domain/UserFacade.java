package rentmovie.userservice.domain;

import lombok.AllArgsConstructor;
import rentmovie.userservice.dto.PostUserDto;
import rentmovie.userservice.infrastructure.security.SecurityUtility;

import java.util.List;

@AllArgsConstructor
public class UserFacade {

    private UserRepository userRepository;

    public User createUser(PostUserDto postUserDto){
        User user = User.builder()
                .id(postUserDto.getId())
                .username(postUserDto.getUsername())
                .email(postUserDto.getEmail())
                .password(SecurityUtility
                        .passwordEncoder()
                        .encode(postUserDto.getPassword()))
                .build();

        return userRepository.insert(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
