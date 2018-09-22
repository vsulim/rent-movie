package rentmovie.userservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rentmovie.userservice.domain.security.UserSecurityService;

@Configuration
public class UserConfiguration {

    public UserFacade accountCreator(){
        UserRepository userRepository = new InMemoryUserRepository();
        return accountCreator(userRepository);
    }

    @Bean
    public UserFacade accountCreator(UserRepository userRepository){
        return new UserFacade(userRepository);
    }

    @Bean
    public UserSecurityService userSecurityService(UserRepository userRepository){
        return new UserSecurityService(userRepository);
    }
}
