package rentmovie.punishmentservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rentmovie.punishmentservice.dto.PunishmentDto;

@RestController
@AllArgsConstructor
@RequestMapping("/punishments")
public class PunishmentController {

    private PunishmentFacade punishmentFacade;

    @GetMapping("/of/{userId}")
    public PunishmentDto getPunishmentsOfUser(@PathVariable String userId) {
        return punishmentFacade.getPunishments(userId);
    }

    @PostMapping("/{userId}")
    public PunishmentDto addPunishment(@PathVariable String userId, @RequestParam("days") long exceededDays) {
        return punishmentFacade.addPunishmentBasedOnExceededDays(userId, exceededDays);
    }

    @PutMapping("/adjust")

}
