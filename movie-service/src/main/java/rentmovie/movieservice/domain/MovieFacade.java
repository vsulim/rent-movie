package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;

@AllArgsConstructor
public class MovieFacade {

    private MovieRepository movieRepository;

    public Page<Movie> findall() {

        return movieRepository.findAll();
    }
}
