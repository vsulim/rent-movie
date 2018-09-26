package rentmovie.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import rentmovie.movieservice.domain.Movie.Director;
import rentmovie.movieservice.domain.MovieGenre;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PostMovieDto {

    @Null
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String genre;

    @NotNull
    private String leadStudio;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int inStockNumber;

    @NotNull
    @Valid
    private DirectorDto director;

    private int audienceScore;

    private String year;
}
