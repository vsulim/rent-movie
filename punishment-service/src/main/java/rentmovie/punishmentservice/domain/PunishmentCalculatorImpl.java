package rentmovie.punishmentservice.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PunishmentCalculatorImpl implements PunishmentCalculator {

    @Override
    public Map<String, String> calculatePunishmentBasedOnDays(long exceededDays) {

        BigDecimal amount = new BigDecimal(0);

        for (int i = 1; i<= exceededDays; i++){
            amount = amount.add(new BigDecimal(5));
        }

        Map<String, String> punishmentData = new HashMap<>();

        punishmentData.put("amount", String.valueOf(amount));
        punishmentData.put("reason", "Rent period exceeded with " + exceededDays + " days.");

        return punishmentData;
    }
}
