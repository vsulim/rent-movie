package rentmovie.userservice.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, String> {

    List<User> findAll();

    User insert(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findById(String userId);

    User save(User user);
}
