package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import rentmovie.rentservice.dto.PostRentDto;

import rentmovie.rentservice.domain.Rent.RentPeriod;
import rentmovie.rentservice.dto.PunishmentDto;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.proxy.PunishmentProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
public class RentManager {

    private MovieProxy movieProxy;
    private PunishmentProxy punishmentProxy;

    public Rent processRent(PostRentDto rentDto) {

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
                isPeriodDateExceeded(rent.getRentDate(), rent.getRentExpirationDate());

        if (numberOfExceededDays > 0){
            punishmentProxy.addPunishment(rent.getUserId(), numberOfExceededDays);
        }

        return rent;
    }

    public void actualizeStockNumber(String movieId, String subtract) {
        movieProxy.actualizeStockNumber(movieId,subtract);
    }

    private long isPeriodDateExceeded(LocalDate rentDate, LocalDate expirationDate){

        LocalDate actualDate = LocalDate.now();

        Period rentPeriod = Period.between(rentDate, expirationDate);
        Period exceedPeriod = Period.between(rentDate, actualDate);

        return exceedPeriod.getDays() - rentPeriod.getDays();
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
