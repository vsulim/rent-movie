package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rentmovie.rentservice.dto.PostRentDto;
import rentmovie.rentservice.dto.RentDto;
import rentmovie.rentservice.proxy.MovieProxy;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class RentFacade {

    private RentRepository rentRepository;
    private MovieProxy movieProxy;
    private RentManager rentManager;

    public RentDto processRent(PostRentDto rentDto) {

        BigDecimal moviePrice = movieProxy.retrieveMoviePrice(rentDto.getMovieId());

        Rent rent = rentManager.processRent(rentDto, moviePrice);
        rentRepository.insert(rent);
        log.info("Added rent with id {} ", rent.getId());

        movieProxy.actualizeStockNumber(rentDto.getMovieId(), "Subtract");

        return rent.convertToDto();
    }

    public RentDto getRent(String id) {
        return rentRepository.findById(id)
                .map(Rent::convertToDto)
                .orElseThrow(() -> new NoSuchElementException());
    }

    public List<String> findMovieIdsOfUser(String userId) {
        return rentRepository.findAllByUserId(userId)
                .stream()
                .map(Rent::getMovieId)
                .collect(Collectors.toList());
    }
}
