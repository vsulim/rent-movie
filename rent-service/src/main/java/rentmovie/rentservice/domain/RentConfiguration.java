package rentmovie.rentservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rentmovie.rentservice.proxy.MovieProxy;

@Configuration
public class RentConfiguration {

    public RentFacade rentFacade(){
        return rentFacade(new InMemoryRentRepository(), null);
    }
    @Bean
    public RentFacade rentFacade(RentRepository rentRepository, MovieProxy movieProxy) {
        RentManager rentManager = new RentManager(movieProxy);

        return new RentFacade(rentRepository, movieProxy, rentManager);
    }
}
