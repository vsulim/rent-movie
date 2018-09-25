package rentmovie.movieservice;

import org.junit.Before;
import org.junit.Test;
import rentmovie.movieservice.domain.Movie;
import rentmovie.movieservice.domain.MovieConfiguration;
import rentmovie.movieservice.domain.MovieFacade;
import rentmovie.movieservice.domain.MovieGenre;

import java.math.BigDecimal;
import java.util.Optional;

import rentmovie.movieservice.domain.Movie.Director;

import static java.util.Optional.*;
import static org.junit.Assert.assertEquals;

public class MovieSpecTest {

    private MovieFacade movieFacade = new MovieConfiguration().movieFacade();

    @Before
    public void setUp(){
        Movie movie = Movie.builder()
                .id("ID1")
                .name("Harry Potter")
                .inStockNumber(10)
                .genre(MovieGenre.FANTASY)
                .leadStudio("Independent")
                .price(new BigDecimal(19.99))
                .year("2015")
                .director(new Director("Jaq Van'ile", 10))
                .build();

        movieFacade.save(movie);
    }

    @Test
    public void shouldSubtractStockNumber(){

        String action = "subtract";
        String movieId = "ID1";

        movieFacade.actualizeInStockNumber(action, movieId);
        Movie movie = movieFacade.findById(movieId);

        assertEquals(9, movie.getInStockNumber());
    }
}
