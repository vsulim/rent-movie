package rentmovie.rentservice;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.dto.PostRentDto;

@RestController
@RequestMapping("/rents")
@AllArgsConstructor
public class RentController {

    private RentFacade rentFacade;
    private MovieProxy movieProxy;

    @PostMapping
    public void rentMovie(@RequestBody PostRentDto rentDto){
        rentFacade.processRent(rentDto);
    }

}
