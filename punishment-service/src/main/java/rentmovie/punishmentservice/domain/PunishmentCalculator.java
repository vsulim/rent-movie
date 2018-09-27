package rentmovie.punishmentservice.domain;

import java.util.Map;

public interface PunishmentCalculator {

    Map<String, String> calculatePunishmentBasedOnDays(long exceededDays);
}
