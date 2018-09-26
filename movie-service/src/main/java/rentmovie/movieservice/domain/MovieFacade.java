package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import rentmovie.movieservice.dto.MovieDto;
import rentmovie.movieservice.dto.PostMovieDto;
import rentmovie.movieservice.exception.MovieNotFoundException;
import rentmovie.movieservice.provider.Converter;
import rentmovie.movieservice.proxy.RentProxy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;
    private MovieManager movieManager;
    private RentProxy rentProxy;
    private Converter<PostMovieDto, MovieDto, Movie> converter;

    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public MovieDto findById(String movieId) {
        return movieRepository.findById(movieId)
                .map(converter::convertToDto)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found."));
    }

    public void actualizeInStockNumber(String action, String movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + movieId + " not found."));

        Movie actualizedMovie = movieManager.actualizeStock(movie, action);

        movieRepository.save(actualizedMovie);
    }

    public void addMovie(PostMovieDto postMovieDto) {

        Movie movie = converter.update(postMovieDto);

        movieRepository.insert(movie);
        log.info("Added movie {} to database" + movie.getName());
    }

    public List<Movie> retrieveAllRentedMoviesOfUser(String userId){

        List<String> movieIdsAssignedToUser = rentProxy.retrievieMoveIds(userId);

        return movieIdsAssignedToUser.stream()
                    .map(id -> movieRepository.findById(id)
                        .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found.")))
                    .collect(Collectors.toList());
    }
}
