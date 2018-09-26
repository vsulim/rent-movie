package rentmovie.movieservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
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
        Director director = new Director("Mark Wolsky", 9);

        Movie movie = createMovie("ID1", "Harry Potter", 10, MovieGenre.FANTASY,
                "Independent", new BigDecimal(14.99), "2015",director);

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

    @Test
    public void shouldAddInStockNumber(){

        String action = "add";
        String movieId = "ID1";

        movieFacade.actualizeInStockNumber(action, movieId);
        Movie movie = movieFacade.findById(movieId);

        assertEquals(11, movie.getInStockNumber());
    }

    private static Movie createMovie(String id, String name, int inStockNumber, MovieGenre genre,
                                     String leadStudio, BigDecimal price, String year, Director director){
        return Movie.builder()
                .id(id)
                .name(name)
                .year(year)
                .genre(genre)
                .price(price)
                .leadStudio(leadStudio)
                .inStockNumber(inStockNumber)
                .director(director)
                .build();
    }
}
