package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import rentmovie.rentservice.dto.PostRentDto;
import rentmovie.rentservice.dto.RentDto;
import rentmovie.rentservice.exception.RentNotFoundException;
import rentmovie.rentservice.exception.RentPunishmentException;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.proxy.PunishmentProxy;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class RentFacade {

    @Getter
    private RentManager rentManager;
    private RentRepository rentRepository;
    private MovieProxy movieProxy;
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
                .orElseThrow(() -> new RentNotFoundException("No rent with id " + id + " found."));
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
                .orElseThrow(() -> new RentNotFoundException("Rent with id " + rentId + " not found."));

        LocalDate actualDate = LocalDate.now();
        Rent processedRent = rentManager.processReturn(rent, actualDate);

        rentRepository.delete(processedRent);
        log.info("Deleted rent with id {} ", rent.getId());

        rentManager.actualizeStockNumber(rent.getMovieId(), "Add");
    }
}
