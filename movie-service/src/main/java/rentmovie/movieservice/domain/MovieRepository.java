package rentmovie.movieservice.domain;

import com.mongodb.Mongo;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.List;

@org.springframework.stereotype.Repository
public interface MovieRepository extends Repository<Movie, String> {

    void saveAll(Iterable<Movie> iterable);

    long count();

    Page<Movie> findAll();
}
