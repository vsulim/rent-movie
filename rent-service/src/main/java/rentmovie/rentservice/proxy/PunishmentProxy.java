package rentmovie.rentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import rentmovie.rentservice.dto.PunishmentDto;

import java.util.List;
import java.util.Optional;

@FeignClient("punishment-service")
public interface PunishmentProxy {

    @GetMapping("/punishments/all/{userId}")
    List<PunishmentDto> findAnyPunishment(@PathVariable("userId") String userId);

    @PostMapping("/punishments/days/")
    PunishmentDto addPunishmentExceededDays(@RequestParam("exceeded") long numberOfExceededDays, @RequestBody PunishmentDto punishmentDto);
}

