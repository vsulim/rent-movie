package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

public class MovieInitializer{

    public MovieInitializer(MovieRepository movieRepository){

        if (movieRepository.count() != 0) {
            return;
        }

        Movie harryPotter = Movie.builder()
                .name("Harry Potter")
                .price(new BigDecimal(15))
                .genre(MovieGenre.SCIENCE_FICTION)
                .description("Adventures of young wizard, Harry Potter," +
                        "and his friends.")
                .build();

        Movie blow = Movie.builder()
                .name("Harry Potter")
                .price(new BigDecimal(15))
                .genre(MovieGenre.CRIME)
                .description("Movie based on facts about George Young," +
                        " that was one of Pablo Escobar partner's")
                .build();

        Movie inception = Movie.builder()
                .name("Inception")
                .price(new BigDecimal(10))
                .genre(MovieGenre.SCIENCE_FICTION)
                .description("The film stars Leonardo DiCaprio as a professional" +
                        " thief who steals information by infiltrating the subconscious.")
                .build();

        movieRepository.saveAll(Arrays.asList(harryPotter, inception, blow));
    }
}
