package rentmovie.rentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rentmovie.rentservice.dto.RentDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "RentMovies")
public class Rent {

    @Id
    private String id;
    private String movieId;
    private String userId;
    private RentPeriod rentPeriod;
    private BigDecimal rentTotalPrice;
    private LocalDate rentDate;
    private LocalDate rentExpirationDate;


    public enum RentPeriod {
        WEEK, TWO_WEEKS, MONTH;

        static RentPeriod toRentalPeriod(String givenRentalPeriod) {
            return Arrays.stream(RentPeriod.values())
                    .filter(period -> isCorrect(period, givenRentalPeriod))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }

        private static boolean isCorrect(RentPeriod period, String givenRentalPeriod) {

            return period.name().equalsIgnoreCase(givenRentalPeriod);
        }
    }

    public RentDto convertToDto() {
        return RentDto.builder()
                .id(id)
                .movieId(movieId)
                .userId(userId)
                .rentPeriod(String.valueOf(rentPeriod))
                .rentTotalPrice(rentTotalPrice)
                .rentDate(rentDate)
                .rentExpirationDate(rentExpirationDate)
                .build();
    }
}
