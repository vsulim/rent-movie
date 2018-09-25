package rentmovie.rentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient("movie-service")
public interface MovieProxy  {

    @GetMapping("/movies/price/{movieId}")
    BigDecimal retrieveMoviePrice(@PathVariable("movieId") String movieId);

    @PutMapping("/movies/actualize/{movieId}")
    void actualizeStockNumber(@PathVariable("movieId") String movieId, @RequestBody String action);
}
