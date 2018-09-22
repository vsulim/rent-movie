package rentmovie.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Null;

@Getter
@Builder
@AllArgsConstructor
public class PostUserDto {

    @Null
    private final String id;

    private final String username;
    private final String password;
    private final String email;

    public PostUserDto() {
        this.id = null;
        this.username = null;
        this.password = null;
        this.email = null;
    }
}
