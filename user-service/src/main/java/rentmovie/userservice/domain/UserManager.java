package rentmovie.userservice.domain;

import rentmovie.userservice.dto.PostUserDto;
import rentmovie.userservice.infrastructure.security.SecurityUtility;

public class UserManager {

    public User create(PostUserDto postUserDto) {
        return User.builder()
                .id(postUserDto.getId())
                .username(postUserDto.getUsername())
                .email(postUserDto.getEmail())
                .password(SecurityUtility
                        .passwordEncoder()
                        .encode(postUserDto.getPassword()))
                .build();
    }
}
