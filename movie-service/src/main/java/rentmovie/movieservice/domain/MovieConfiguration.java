package rentmovie.movieservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import rentmovie.movieservice.dto.MovieDto;
import rentmovie.movieservice.dto.PostMovieDto;
import rentmovie.movieservice.provider.Converter;
import rentmovie.movieservice.provider.MovieConverter;
import rentmovie.movieservice.proxy.RentProxy;

@Configuration
public class MovieConfiguration {

    @Bean
    public MovieInitializer movieInitializer(MovieRepository movieRepository) throws Exception {
        return new MovieInitializer(movieRepository);
    }

    @Bean
    public MovieFacade movieFacade(MovieRepository movieRepository,RentProxy rentProxy) {

        MovieManager movieManager = new MovieManager();
        Converter<PostMovieDto, MovieDto, Movie> converter = new MovieConverter();

        return new MovieFacade(movieRepository, movieManager, rentProxy, converter);
    }

    public MovieFacade movieFacade() {
        return movieFacade(new InMemoryMovieRepository(), null);
    }
}
