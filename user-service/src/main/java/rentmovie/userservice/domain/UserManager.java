package rentmovie.userservice.domain;

import rentmovie.userservice.dto.PostUserDto;
import rentmovie.userservice.infrastructure.security.SecurityUtility;

import java.util.ArrayList;
import java.util.List;

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

    public User updateUsersPunishments(User user, String punishmentId) {

        List<String> punishments = user.getPunishmentsId();

        if (punishments != null) {
            punishments.add(punishmentId);
        } else {
            punishments = new ArrayList<>();
            punishments.add(punishmentId);
        }

        return user.withPunishmentsId(punishments);
    }

    public User removeUserPunishment(User user, String punishmentId) {

        List<String> punishments = user.getPunishmentsId();

        punishments.remove(punishmentId);

        return user.withPunishmentsId(punishments);
}
}
