package rentmovie.userservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PostUserDto {

    @Null
    private String id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;
}
