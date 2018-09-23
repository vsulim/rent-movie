package rentmovie.movieservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MovieController {

    private MovieFacade movieFacade;

    public Page<Movie> getAllMovies(){
        return movieFacade.findall();
    }
}
