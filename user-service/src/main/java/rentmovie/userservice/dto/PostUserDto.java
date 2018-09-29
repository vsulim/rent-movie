package rentmovie.userservice.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
    private String password;

    @Email
    @NotNull
    private String email;
}
