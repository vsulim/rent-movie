package rentmovie.rentservice.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRentRepository implements RentRepository {

    private Map<String, Rent> rents = new ConcurrentHashMap<>();

    @Override
    public void insert(Rent rent) {
        rents.put(rent.getId(), rent);
    }

    @Override
    public Optional<Rent> findById(String id) {
        return Optional.ofNullable(rents.get(id));
    }

    @Override
    public Optional<Rent> findByUserIdAndMovieId(String userId, String movieId) {
        return Optional.empty();
    }

    @Override
    public List<Rent> findAllByUserId(String userId) {
        return null;
    }

    @Override
    public void delete(Rent processedRent) {
        rents.remove(processedRent);
    }
}
