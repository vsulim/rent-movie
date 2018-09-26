package rentmovie.rentservice.domain;

import org.springframework.data.repository.Repository;
import rentmovie.rentservice.dto.RentDto;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends Repository<Rent, String> {

    void insert(Rent rent);

    Optional<Rent> findById(String id);

    List<Rent> findAllByUserId(String userId);
}
