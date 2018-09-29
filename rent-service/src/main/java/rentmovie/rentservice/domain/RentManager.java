package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import rentmovie.rentservice.dto.PostRentDto;

import rentmovie.rentservice.domain.Rent.RentPeriod;
import rentmovie.rentservice.dto.PunishmentDto;
import rentmovie.rentservice.exception.RentPunishmentException;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.proxy.PunishmentProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
public class RentManager {

    private MovieProxy movieProxy;
    private PunishmentProxy punishmentProxy;

    public Rent processRent(PostRentDto rentDto) {

        List<PunishmentDto> punishments =
                punishmentProxy.findAnyPunishment(rentDto.getUserId());

        if (!punishments.isEmpty()) {
            throw new RentPunishmentException("You can't rent a movie. \nPlease adjust your punishments:\n");
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

    public Rent processReturn(Rent rent, LocalDate actualDate) {

        long numberOfExceededDays =
                countIfPeriodDateExceeded(rent.getRentDate(), rent.getRentExpirationDate(), actualDate);

        if (numberOfExceededDays > 0){

            PunishmentDto punishmentDto = PunishmentDto.builder()
                    .userId(rent.getUserId())
                    .build();

            punishmentProxy.addPunishmentExceededDays(numberOfExceededDays, punishmentDto);
        }

        return rent;
    }

    public void actualizeStockNumber(String movieId, String action) {
        movieProxy.actualizeStockNumber(movieId, action);
    }


    private long countIfPeriodDateExceeded(LocalDate rentDate, LocalDate expirationDate, LocalDate actualDate){

        Long rentPeriodDays = ChronoUnit.DAYS.between(rentDate, expirationDate);
        Long exceedPeriodDays = ChronoUnit.DAYS.between(rentDate, actualDate);

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
