package rentmovie.userservice.domain;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
public interface UserRepository extends Repository<User, String> {

    List<User> findAll();

    User insert(User user);

    Optional<User> findByUsername(String username);
}
