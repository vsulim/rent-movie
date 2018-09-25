package rentmovie.rentservice;

import org.springframework.data.repository.Repository;

public interface RentRepository extends Repository<Rent, String> {

    void save(Rent rent);
}
