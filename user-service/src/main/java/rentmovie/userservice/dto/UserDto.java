package rentmovie.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UserDto {

    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> punishmentsId;
}
