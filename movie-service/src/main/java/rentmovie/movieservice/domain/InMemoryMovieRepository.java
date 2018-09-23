package rentmovie.movieservice.domain;

import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMovieRepository implements MovieRepository {

    Map<String, Movie> movies = new ConcurrentHashMap<>();

    @Override
    public Page<Movie> findAll() {
        return null;
    }
    
    @Override
    public void saveAll(Iterable<Movie> iterable) {

    }

    @Override
    public long count() {
        return 0;
    }

}
