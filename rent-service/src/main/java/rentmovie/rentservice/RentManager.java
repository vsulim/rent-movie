package rentmovie.rentservice;

import rentmovie.rentservice.dto.PostRentDto;

import rentmovie.rentservice.Rent.RentalPeriod;

import java.math.BigDecimal;

public class RentManager {

    public Rent processRent(PostRentDto rentDto, BigDecimal moviePrice){

        return Rent.builder()
                .id(rentDto.getId())
                .movieId(rentDto.getMovieId())
                .userId(rentDto.getUserId())
                .rentalPeriod(RentalPeriod.toRentalPeriod(rentDto.getRentalPeriod()))
                .rentalTotalPrice(calculateTotalPrice(moviePrice, rentDto.getRentalPeriod()))
                .build();
    }

    private BigDecimal calculateTotalPrice(BigDecimal moviePrice, String rentalPeriod) {

        RentalPeriod period = RentalPeriod.toRentalPeriod(rentalPeriod);

        if (period.equals(RentalPeriod.TWO_WEEKS)){
            moviePrice = moviePrice.multiply(new BigDecimal(2));

        } else if (period.equals(RentalPeriod.MONTH)){
            moviePrice = moviePrice.multiply(new BigDecimal(3));
        }

        return moviePrice;
    }
}
