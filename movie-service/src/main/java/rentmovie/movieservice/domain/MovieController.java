package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import rentmovie.movieservice.dto.MovieDto;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private MovieFacade movieFacade;

    @GetMapping
    public Page<Movie> getAllMovies(Pageable pageable){
        return movieFacade.findAll(pageable);
    }

    @GetMapping("/{id}")
    public MovieDto getMovie(@PathVariable String id){
        return movieFacade.findById(id);
    }

    @GetMapping("/price/{movieId}")
    public BigDecimal getMoviePrice(@PathVariable String movieId) {

        MovieDto movie = movieFacade.findById(movieId);

        return movie.getPrice();
    }

    @PutMapping("/actualize/{movieId}")
    public void actualizeStockNumber(@PathVariable String movieId, @RequestBody String action) {
        movieFacade.actualizeInStockNumber(action, movieId);
    }

    @GetMapping("/of/{userId}")
    public List<MovieDto> getMoviesOfUser(@PathVariable String userId){
        return movieFacade.findAllRentedMoviesOfUser(userId);
    }

}
