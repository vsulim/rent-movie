package rentmovie.userservice.domain;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rentmovie.userservice.dto.UserDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Value
@Builder
@Wither
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "Users")
public class User implements UserDetails {

    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> punishmentsId;

    @CreatedDate
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDto convertToDto() {
        return UserDto.builder()
                .id(id)
                .email(email)
                .username(username)
                .password(password)
                .punishmentsId(punishmentsId)
                .build();
    }
}
