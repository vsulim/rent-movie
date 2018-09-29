package rentmovie.punishmentservice.domain;

import lombok.AllArgsConstructor;
import rentmovie.punishmentservice.dto.PunishmentDto;
import rentmovie.punishmentservice.proxy.UserProxy;

import java.math.BigDecimal;

@AllArgsConstructor
public class PunishmentManager {

    private UserProxy userProxy;

    public Punishment createPunishment(PunishmentDto punishmentDto, String amount, String reason) {
            return Punishment.builder()
                    .id(punishmentDto.getId())
                    .userId(punishmentDto.getUserId())
                    .punishmentReason(reason)
                .punishmentAmount(new BigDecimal(amount))
                .build();
    }

    public void finishPunishmentProcess(String userId, String punishmentId) {
        userProxy.addUserPunishment(userId, punishmentId);
    }

    public void processAdjustPunishment(String userId, String punishmentId) {
        userProxy.removeUserPunishment(userId, punishmentId);
    }
}
