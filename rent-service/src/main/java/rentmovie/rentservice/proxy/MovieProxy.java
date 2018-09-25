package rentmovie.rentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.math.BigDecimal;

@FeignClient("movie-service")
public interface MovieProxy  {

    @GetMapping("/movies/price/{movieId}")
    BigDecimal retrieveMoviePrice(@PathVariable("movieId") String movieId);

    @PutMapping
    void actualizeStockLevel();
}
