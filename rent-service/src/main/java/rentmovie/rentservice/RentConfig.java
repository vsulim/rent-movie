package rentmovie.rentservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rentmovie.rentservice.proxy.MovieProxy;

@Configuration
public class RentConfig {

    @Bean
    public RentFacade rentMovieConfig(RentRepository rentRepository,
                                      MovieProxy movieProxy){

        RentManager rentManager = new RentManager();
        return new RentFacade(rentRepository, movieProxy, rentManager);
    }
}
