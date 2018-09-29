package rentmovie.punishmentservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rentmovie.punishmentservice.dto.PunishmentDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/punishments")
public class PunishmentController {

    private PunishmentFacade punishmentFacade;

    @GetMapping("/all/{userId}")
    public List<PunishmentDto> getUserPunishments(@PathVariable String userId) {
        return punishmentFacade.findAllUserPunishments(userId);
    }

    @PostMapping("/days/")
    public PunishmentDto addPunishmentExceededDays(@RequestParam("exceeded") long exceededDays, @RequestBody PunishmentDto punishmentDto) {
        return punishmentFacade.addPunishmentBasedOnExceededDays(punishmentDto, exceededDays);
    }

    @DeleteMapping("/adjust")
    public void payOffPunishment(@RequestParam("punishmentId") String punishmentId){
        punishmentFacade.adjustPunishment(punishmentId);
    }
}
