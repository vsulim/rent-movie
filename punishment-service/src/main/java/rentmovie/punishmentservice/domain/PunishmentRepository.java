package rentmovie.punishmentservice.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface PunishmentRepository extends Repository<Punishment, String> {

    Punishment findByUserId(String userId);

    Punishment insert(Punishment punishment);
}
