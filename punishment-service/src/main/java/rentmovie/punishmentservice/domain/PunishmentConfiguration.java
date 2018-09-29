package rentmovie.punishmentservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rentmovie.punishmentservice.proxy.UserProxy;

@Configuration
public class PunishmentConfiguration {

    public PunishmentFacade punishmentFacade(){
        return punishmentFacade(new InMemoryPunishmentRepository(), null);
    }
    @Bean
    public PunishmentFacade punishmentFacade(PunishmentRepository punishmentRepository, UserProxy userProxy){

        PunishmentManager punishmentManager = new PunishmentManager(userProxy);
        PunishmentCalculator punishmentAmountCalculator = new PunishmentCalculatorImpl();

        return new PunishmentFacade(punishmentManager, punishmentRepository, punishmentAmountCalculator, userProxy);
    }
}
