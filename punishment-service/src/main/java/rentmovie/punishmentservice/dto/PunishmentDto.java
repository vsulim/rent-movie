package rentmovie.punishmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PunishmentDto implements Serializable {

    private String id;
    private String userId;
    private BigDecimal punishmentAmount;
    private String punishmentReason;
}
