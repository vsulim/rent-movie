package rentmovie.rentservice;

import lombok.AllArgsConstructor;
import rentmovie.rentservice.dto.PostRentDto;
import rentmovie.rentservice.proxy.MovieProxy;

import java.math.BigDecimal;

@AllArgsConstructor
public class RentFacade {

    private RentRepository rentRepository;
    private MovieProxy movieProxy;
    private RentManager rentManager;

    public void processRent(PostRentDto rentDto) {

        BigDecimal moviePrice = movieProxy
                .retrieveMoviePrice(rentDto.getMovieId());

        Rent rent = rentManager.processRent(rentDto, moviePrice);
        rentRepository.save(rent);

        movieProxy.actualizeStockLevel();
    }
}
