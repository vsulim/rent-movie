package rentmovie.rentservice;

import org.apache.tomcat.jni.Local;
import rentmovie.rentservice.dto.PostRentDto;

import rentmovie.rentservice.Rent.RentalPeriod;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentManager {

    public Rent processRent(PostRentDto rentDto, BigDecimal moviePrice) {

        RentalPeriod period = RentalPeriod.toRentalPeriod(rentDto.getRentalPeriod());

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

    private BigDecimal calculateTotalPrice(BigDecimal moviePrice, RentalPeriod period) {

        if (period.equals(RentalPeriod.TWO_WEEKS)) {
            moviePrice = moviePrice.multiply(new BigDecimal(2));

        } else if (period.equals(RentalPeriod.MONTH)) {
            moviePrice = moviePrice.multiply(new BigDecimal(3));
        }

        return moviePrice;
    }

    private LocalDate calculateExpirationDate(RentalPeriod period) {

        if (period.equals(RentalPeriod.WEEK)) {
            return LocalDate.now().plusDays(7);

        } else if (period.equals(RentalPeriod.TWO_WEEKS)) {
            return LocalDate.now().plusDays(14);
        }

        return LocalDate.now().plusDays(30);
    }
}
