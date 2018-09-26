package rentmovie.movieservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("rent-service")
public interface RentProxy {

    @GetMapping("/rents/movie_ids/of/{userId}")
    List<String> retrieveMoveIds(@PathVariable("userId") String userId);
}
