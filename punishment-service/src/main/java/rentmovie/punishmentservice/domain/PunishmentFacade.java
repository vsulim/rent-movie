package rentmovie.punishmentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.springframework.transaction.annotation.Transactional;
import rentmovie.punishmentservice.dto.PunishmentDto;
import rentmovie.punishmentservice.proxy.UserProxy;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PunishmentFacade {

    @Getter
    private PunishmentManager punishmentManager;
    private PunishmentRepository punishmentRepository;
    private PunishmentCalculator punishmentCalculator;
    private UserProxy userProxy;


    public PunishmentDto addPunishmentBasedOnExceededDays(PunishmentDto punishmentDto, long exceededDays) {

        Map<String, String> punishmentData = punishmentCalculator.calculatePunishmentBasedOnDays(exceededDays);

        Punishment punishment =
                punishmentManager.createPunishment(punishmentDto, punishmentData.get("amount"), punishmentData.get("reason"));

        Punishment processedPunishment = punishmentRepository.insert(punishment);

        punishmentManager.finishPunishmentProcess(processedPunishment.getUserId(), processedPunishment.getId());

        return punishment.convertToDto();
    }

    @Transactional
    public void adjustPunishment(String punishmentId) {

        Punishment punishment = punishmentRepository.findById(punishmentId)
                .orElseThrow(() -> new RuntimeException("Punishment with id + " + punishmentId + " not found."));

        punishmentManager.processAdjustPunishment(punishment.getUserId(), punishmentId);

        punishmentRepository.delete(punishment);
    }

    public List<PunishmentDto> findAllUserPunishments(String userId) {

        List<Punishment> punishments = punishmentRepository.findAllByUserId(userId);

        if (punishments.isEmpty()){
            return Collections.emptyList();
        }

        return punishments.stream()
                .map(Punishment::convertToDto)
                .collect(Collectors.toList());
    }

    public PunishmentDto findById(String punishmentId) {
        return punishmentRepository.findById(punishmentId)
                .map(Punishment::convertToDto)
                .orElseThrow(() -> new RuntimeException("Punishment with id + " + punishmentId + " not found."));
    }
}
