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
import rentmovie.movieservice.dto.DirectorDto;
import rentmovie.movieservice.dto.MovieDto;
import rentmovie.movieservice.dto.PostMovieDto;
import rentmovie.movieservice.exception.MovieNotFoundException;

import static java.util.Optional.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MovieSpecTest {

    private MovieFacade movieFacade = new MovieConfiguration().movieFacade();

    @Before
    public void setUp(){

        DirectorDto director = new DirectorDto("Mark Wolsky", 9);

        PostMovieDto movie = createMovie("ID1", "Harry Potter", 10, "Fantasy",
                "Independent", new BigDecimal(14.99), "2015",director);

        movieFacade.addMovie(movie);
    }

    @Test
    public void shouldSubtractStockNumber( ){

        String action = "subtract";
        String movieId = "ID1";

        movieFacade.actualizeInStockNumber(action, movieId);
        MovieDto movieDto = movieFacade.findById(movieId);

        assertEquals(9, movieDto.getInStockNumber());
    }

    @Test
    public void shouldAddInStockNumber() {

        String action = "add";
        String movieId = "ID1";

        movieFacade.actualizeInStockNumber(action, movieId);
        MovieDto movieDto = movieFacade.findById(movieId);

        assertEquals(11, movieDto.getInStockNumber());
    }

    @Test(expected = MovieNotFoundException.class)
    public void shouldThrowMovieNotFoundException() {

        String exampleId = "gdfasd214asd";

        movieFacade.findById(exampleId);
    }

    private static PostMovieDto createMovie(String id, String name, int inStockNumber, String genre,
                                     String leadStudio, BigDecimal price, String year, DirectorDto director){
        return PostMovieDto.builder()
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
