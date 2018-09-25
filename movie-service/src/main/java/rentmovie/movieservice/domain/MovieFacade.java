package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.NoSuchElementException;

@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;
    private MovieManager movieManager;

    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Movie findById(String movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(NoSuchElementException::new); // add own exception
    }

    public void actualizeInStockNumber(String action, String movieId) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(NoSuchElementException::new);

        Movie actualizedMovie = movieManager.actualizeStock(movie, action);

        movieRepository.save(actualizedMovie);
    }

    public void save(Movie movie){
        movieRepository.insert(movie);
    }
}
