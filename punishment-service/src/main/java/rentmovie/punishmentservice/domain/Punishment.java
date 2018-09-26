package rentmovie.punishmentservice.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "Punishments")
public class Punishment {

    @Id
    private String id;
    private String userId;
    private BigDecimal punishmentAmount;
}
