package rentmovie.movieservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

@Configuration
public class MovieConfiguration {

    @Bean
    public MovieInitializer movieInitializer(MovieRepository movieRepository) throws Exception {
        return new MovieInitializer(movieRepository);
    }

    @Bean
    public MovieFacade movieFacade(MovieRepository movieRepository) {
        MovieManager movieManager = new MovieManager();

        return new MovieFacade(movieRepository, movieManager);
    }

    public MovieFacade movieFacade() {
        return movieFacade(new InMemoryMovieRepository());
    }
}
