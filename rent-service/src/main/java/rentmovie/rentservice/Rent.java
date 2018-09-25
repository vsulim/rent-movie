package rentmovie.rentservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;

@Valid
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "UserFilm_ids")
public class Rent {

    @Id
    private String id;
    private String movieId;
    private String userId;
    private RentalPeriod rentalPeriod;
    private BigDecimal rentalTotalPrice;

    public enum RentalPeriod{
        WEEK, TWO_WEEKS, MONTH;

        static RentalPeriod toRentalPeriod(String givenRentalPeriod){
            return Arrays.stream(RentalPeriod.values())
                    .filter(period -> isCorrect(period, givenRentalPeriod))
                    .findAny()
                    .orElseThrow(IllegalArgumentException::new);
        }

        private static boolean isCorrect(RentalPeriod period, String givenRentalPeriod) {

            return period.name().equalsIgnoreCase(givenRentalPeriod);
        }
    }
}
