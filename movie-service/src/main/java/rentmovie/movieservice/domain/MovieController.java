package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public BigDecimal getMoviePrice(@PathVariable String movieId) {

        Movie movie = movieFacade.findById(movieId);

        return movie.getPrice();
    }

    @PutMapping("/actualize/{movieId}")
    public void actualizeStockNumber(@PathVariable String movieId, @RequestBody String action) {
        movieFacade.actualizeInStockNumber(action, movieId);
    }

}
