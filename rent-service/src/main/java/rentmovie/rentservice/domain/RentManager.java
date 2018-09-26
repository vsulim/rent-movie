package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rentmovie.rentservice.dto.PostRentDto;

import rentmovie.rentservice.domain.Rent.RentPeriod;
import rentmovie.rentservice.proxy.MovieProxy;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
public class RentManager {

    private MovieProxy movieProxy;

    public Rent processRent(PostRentDto rentDto, BigDecimal moviePrice) {

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
