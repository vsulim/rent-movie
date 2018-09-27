package rentmovie.rentservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.proxy.PunishmentProxy;

@Configuration
public class RentConfiguration {

    public RentFacade rentFacade(){
        return rentFacade(new InMemoryRentRepository(), null,null);
    }

    @Bean
    public RentFacade rentFacade(RentRepository rentRepository, MovieProxy movieProxy,PunishmentProxy punishmentProxy) {

        RentManager rentManager = new RentManager(movieProxy, punishmentProxy);

        return new RentFacade(rentManager, movieProxy, rentRepository, punishmentProxy);
    }

//    @Bean
//    public RentManager rentManager(MovieProxy movieProxy, PunishmentProxy punishmentProxy){
//        return new RentManager(movieProxy,);
//    }

}
