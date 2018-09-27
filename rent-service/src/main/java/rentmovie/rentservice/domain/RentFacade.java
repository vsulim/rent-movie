package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import rentmovie.rentservice.dto.PostRentDto;
import rentmovie.rentservice.dto.PunishmentDto;
import rentmovie.rentservice.dto.RentDto;
import rentmovie.rentservice.exception.UserRentPunishmentException;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.proxy.PunishmentProxy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class RentFacade {

    @Getter
    private RentManager rentManager;
    private MovieProxy movieProxy;
    private RentRepository rentRepository;
    private PunishmentProxy punishmentProxy;

    public RentDto processRent(PostRentDto rentDto) {

        Rent rent = rentManager.processRent(rentDto);

        rentRepository.insert(rent);
        log.info("Added rent with id {} ", rent.getId());

        rentManager.actualizeStockNumber(rentDto.getMovieId(), "Subtract");

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

    @Transactional
    public void processReturnRentedMovie(String rentId) {

        Rent rent = rentRepository.findById(rentId)
                .orElseThrow(() -> new NoSuchElementException());

        Rent processedRent = rentManager.processReturn(rent);

        rentRepository.delete(processedRent);
    }
}
