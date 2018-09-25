package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private MovieFacade movieFacade;

    @GetMapping
    public Page<Movie> getAllMovies(Pageable pageable){
        return movieFacade.findAll(pageable);
    }

    @GetMapping("/price/{movieId}")
    public BigDecimal getMoviePrice(@PathVariable String movieId){
        Movie movie = movieFacade.findById(movieId);

        return movie.getPrice();
    }
}
