package rentmovie.punishmentservice.domain;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import rentmovie.punishmentservice.dto.PunishmentDto;


import java.util.Map;

@AllArgsConstructor
public class PunishmentFacade {

    @Delegate
    private PunishmentRepository punishmentRepository;
    private PunishmentManager punishmentManager;
    private PunishmentCalculator punishmentCalculator;

    public PunishmentDto getPunishments(String userId) {

        Punishment punishment = punishmentRepository.findByUserId(userId);

        if (punishment == null){
            return null;
        }

        return punishment.convertToDto();
    }

    public PunishmentDto addPunishmentBasedOnExceededDays(String userId, long exceededDays) {

        Map<String, String> punishmentData = punishmentCalculator.calculatePunishmentBasedOnDays(exceededDays);

        Punishment punishment =
                punishmentManager.create(userId, punishmentData.get("amount"), punishmentData.get("reason"));

        return punishmentRepository.insert(punishment)
                .convertToDto();
    }
}
