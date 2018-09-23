package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.math.BigDecimal;


@Valid
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "Movies")
public class Movie {

    @Id
    private String id;
    private String name;
    private MovieGenre genre;
    private BigDecimal price;
    private String description;
}