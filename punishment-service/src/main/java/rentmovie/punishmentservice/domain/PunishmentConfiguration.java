package rentmovie.punishmentservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PunishmentConfiguration {

    @Bean
    public PunishmentFacade punishmentFacade(PunishmentRepository punishmentRepository){

        PunishmentManager punishmentManager = new PunishmentManager();
        PunishmentCalculator punishmentAmountCalculator = new PunishmentCalculatorImpl();

        return new PunishmentFacade(punishmentRepository, punishmentManager, punishmentAmountCalculator);
    }
}
