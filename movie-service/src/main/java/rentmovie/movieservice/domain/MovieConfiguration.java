package rentmovie.movieservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

@Configuration
public class MovieConfiguration {

    @Bean
    public MovieInitializer movieInitializer(MovieRepository movieRepository){
        return new MovieInitializer(movieRepository);
    }

    @Bean
    public MovieFacade movieFacade(MovieRepository movieRepository){
        return new MovieFacade(movieRepository);
    }

    public MovieFacade movieFacade(){
        return movieFacade(new InMemoryMovieRepository());
    }
}
