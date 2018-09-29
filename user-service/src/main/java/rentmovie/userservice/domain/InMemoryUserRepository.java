package rentmovie.userservice.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {

    private Map<String,User> users = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {

        users.put(user.getId(), user);

        return user;
    }

    @Override
    public List<User> findAll() {
        return users.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public User insert(User user) {

        users.put(user.getId(), user);

        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.values()
                .stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findAny();
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }
}
