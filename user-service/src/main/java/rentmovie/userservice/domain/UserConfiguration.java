package rentmovie.userservice.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rentmovie.userservice.domain.security.UserSecurityService;

@Configuration
public class UserConfiguration {

    public UserFacade userFacade(){
        return userFacade(new InMemoryUserRepository());
    }

    @Bean
    public UserFacade userFacade(UserRepository userRepository) {

        UserManager userManager = new UserManager();

        return new UserFacade(userRepository, userManager);
    }

    @Bean
    public UserSecurityService userSecurityService(UserRepository userRepository) {

        UserManager userManager = new UserManager();

        return new UserSecurityService(userRepository);
    }
}
