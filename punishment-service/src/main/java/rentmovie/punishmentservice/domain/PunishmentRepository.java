package rentmovie.punishmentservice.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PunishmentRepository extends Repository<Punishment, String> {

    Optional<Punishment> findByUserId(String userId);

    Punishment insert(Punishment punishment);

    List<Punishment> findAllByUserId(String userId);

    Optional<Punishment> findById(String punishmentId);

    void delete(Punishment punishment);
}
