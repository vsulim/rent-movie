package rentmovie.movieservice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMovieRepository implements MovieRepository {

    private Map<String, Movie> movies = new ConcurrentHashMap<>();

    @Override
    public void saveAll(Iterable<Movie> iterable) {

    }

    @Override
    public long count() {
        return movies.size();
    }

    @Override
    public Page<Movie> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Movie> findById(String movieId) {
        return Optional.ofNullable(movies.get(movieId));
    }

    @Override
    public void save(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    @Override
    public void insert(Movie movie) {
        movies.put(movie.getId(), movie);
    }

}
