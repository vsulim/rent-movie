package rentmovie.userservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rentmovie.userservice.domain.security.UserSecurityService;

@Configuration
public class UserConfiguration {

    public UserFacade accountCreator(){
        UserRepository userRepository = new InMemoryUserRepository();

        return userFacade(userRepository);
    }

    @Bean
    public UserFacade userFacade(UserRepository userRepository) {
        UserCreator userCreator = new UserCreator();

        return new UserFacade(userRepository,userCreator);
    }

    @Bean
    public UserSecurityService userSecurityService(UserRepository userRepository) {
        UserCreator userCreator = new UserCreator();

        return new UserSecurityService(userRepository);
    }
}
