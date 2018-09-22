package rentmovie.rentalservice;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@Valid
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "UserFilm_ids")
public class RentMovie {

    @Id
    private String id;
    private String filmdId;
    private String userId;
    private BigDecimal rentalTotalPrice;
}
