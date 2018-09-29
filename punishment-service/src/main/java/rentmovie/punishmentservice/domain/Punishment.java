package rentmovie.punishmentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import rentmovie.punishmentservice.dto.PunishmentDto;

import java.math.BigDecimal;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Document(collection = "Punishments")
public class Punishment {

    @Id
    private String id;
    private String userId;
    private BigDecimal punishmentAmount;
    private String punishmentReason;

    public PunishmentDto convertToDto(){
        return PunishmentDto.builder()
                .id(id)
                .userId(userId)
                .punishmentAmount(punishmentAmount)
                .punishmentReason(punishmentReason)
                .build();
    }
}
