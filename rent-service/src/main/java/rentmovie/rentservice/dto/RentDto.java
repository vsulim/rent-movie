package rentmovie.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class RentDto implements Serializable {

    private String id;
    private String userId;
    private String movieId;
    private String rentPeriod;
    private BigDecimal rentTotalPrice;

    private LocalDate rentDate;
    private LocalDate rentExpirationDate;
}
