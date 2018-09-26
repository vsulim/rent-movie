package rentmovie.movieservice.domain;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rentmovie.movieservice.dto.MovieDto;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Value
@Wither
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@EqualsAndHashCode(of = "id")
@Document(collection = "Movies")
public class Movie {

    @Id
    private String id;
    private String name;
    private MovieGenre genre;
    private String leadStudio;
    private BigDecimal price;
    private int audienceScore;
    private int inStockNumber;
    private Director director;
    private String year;

    @Value
    public static class Director {

        private String directorName;
        private int directorRate;
    }
}