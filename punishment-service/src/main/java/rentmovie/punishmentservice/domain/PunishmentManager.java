package rentmovie.punishmentservice.domain;

import java.math.BigDecimal;

public class PunishmentManager {

    public Punishment create(String userId, String amount, String reason) {
        return Punishment.builder()
                .userId(userId)
                .punishmentReason(reason)
                .punishmentAmount(new BigDecimal(amount))
                .build();
    }
}
