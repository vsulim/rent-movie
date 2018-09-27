package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import rentmovie.rentservice.dto.PostRentDto;

import rentmovie.rentservice.domain.Rent.RentPeriod;
import rentmovie.rentservice.dto.PunishmentDto;
import rentmovie.rentservice.exception.UserRentPunishmentException;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.proxy.PunishmentProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@AllArgsConstructor
public class RentManager {

    private MovieProxy movieProxy;
    private PunishmentProxy punishmentProxy;

    public Rent processRent(PostRentDto rentDto) {

        Optional<PunishmentDto> punishment =
                Optional.ofNullable(punishmentProxy.findAnyPunishment(rentDto.getUserId()));

        if (punishment.isPresent()) {
            throw new UserRentPunishmentException("You can't rent a movie. " +
                    "Please adjust your punishment: " + punishment.get().getPunishmentAmount());
        }

        BigDecimal moviePrice = movieProxy.retrieveMoviePrice(rentDto.getMovieId());

        RentPeriod period = RentPeriod.toRentalPeriod(rentDto.getRentPeriod());

        return Rent.builder()
                .id(rentDto.getId())
                .movieId(rentDto.getMovieId())
                .userId(rentDto.getUserId())
                .rentPeriod(period)
                .rentTotalPrice(calculateTotalPrice(moviePrice, period))
                .rentExpirationDate(calculateExpirationDate(period))
                .rentDate(LocalDate.now())
                .build();
    }

    public Rent processReturn(Rent rent) {

        long numberOfExceededDays =
                countIfPeriodDateExceeded(rent.getRentDate(), rent.getRentExpirationDate());

        if (numberOfExceededDays > 0){
            punishmentProxy.addPunishment(numberOfExceededDays, rent.getUserId());
        }

        return rent;
    }

    public void actualizeStockNumber(String movieId, String subtract) {
        movieProxy.actualizeStockNumber(movieId,subtract);
    }


    private long countIfPeriodDateExceeded(LocalDate rentDate, LocalDate expirationDate){
//        LocalDate actualDate = LocalDate.now().plusDays(70);

        LocalDate actualDate = LocalDate.now();

        Long rentPeriodDays = ChronoUnit.DAYS.between(rentDate, expirationDate);
        Long exceedPeriodDays = ChronoUnit.DAYS.between(expirationDate, actualDate);

        return exceedPeriodDays - rentPeriodDays;
    }

    private BigDecimal calculateTotalPrice(BigDecimal moviePrice, RentPeriod period) {

        if (period.equals(RentPeriod.TWO_WEEKS)) {
            moviePrice = moviePrice.multiply(new BigDecimal(2));

        } else if (period.equals(RentPeriod.MONTH)) {
            moviePrice = moviePrice.multiply(new BigDecimal(3));
        }

        return moviePrice;
    }

    private LocalDate calculateExpirationDate(RentPeriod period) {

        if (period.equals(RentPeriod.WEEK)) {
            return LocalDate.now().plusDays(7);

        } else if (period.equals(RentPeriod.TWO_WEEKS)) {
            return LocalDate.now().plusDays(14);
        }

        return LocalDate.now().plusDays(30);
    }

}
