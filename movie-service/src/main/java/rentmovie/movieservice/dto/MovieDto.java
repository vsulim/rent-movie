package rentmovie.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import rentmovie.movieservice.domain.Movie.Director;
import rentmovie.movieservice.domain.MovieGenre;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MovieDto {

    private String id;
    private String name;
    private String genre;
    private String leadStudio;
    private BigDecimal price;
    private int audienceScore;
    private int inStockNumber;
    private DirectorDto director;
    private String year;
}
