package rentmovie.punishmentservice.domain;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryPunishmentRepository implements PunishmentRepository {

    private Map<String, Punishment> punishments = new ConcurrentHashMap<>();

    @Override
    public Optional<Punishment> findByUserId(String userId) {
        return punishments.values()
                .stream()
                .filter(punishment -> punishment.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public Punishment insert(Punishment toSave) {

         punishments.put(toSave.getId(), toSave);

         return toSave;
    }

    @Override
    public List<Punishment> findAllByUserId(String userId) {
        return punishments.values()
                .stream()
                .filter(punishment -> punishment
                        .getUserId()
                        .equalsIgnoreCase(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Punishment> findById(String punishmentId) {
        return Optional.ofNullable(punishments.get(punishmentId));
    }

    @Override
    public void delete(Punishment punishment) {
        punishments.remove(punishment);
    }
}
