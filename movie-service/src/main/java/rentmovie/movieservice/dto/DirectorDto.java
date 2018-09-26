package rentmovie.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class DirectorDto {

    @NotNull
    private String directorName;

    private int directorRate;
}
