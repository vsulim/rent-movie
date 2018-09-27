package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rentmovie.rentservice.dto.PostRentDto;
import rentmovie.rentservice.dto.ReturnRentDto;

import java.util.List;

@RestController
@RequestMapping("/rents")
@AllArgsConstructor
public class RentController {

    private RentFacade rentFacade;

    @PostMapping
    public void rentMovie(@RequestBody PostRentDto rentDto){
        rentFacade.processRent(rentDto);
    }

    @GetMapping("movie_ids/of/{userId}")
    public List<String> getMovieIdsOfUser(@PathVariable String userId) {
        return rentFacade.findMovieIdsOfUser(userId);
    }

    @DeleteMapping("/return")
    public void returnRentedMovie(@RequestParam("rentId") String rentId){
        rentFacade.processReturnRentedMovie(rentId);
    }
}
