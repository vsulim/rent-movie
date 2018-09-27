package rentmovie.rentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rentmovie.rentservice.dto.PunishmentDto;

import java.util.Optional;

@FeignClient("punishment-service")
public interface PunishmentProxy {

    @GetMapping("/punishments/of/{userId}")
    PunishmentDto findAnyPunishment(@PathVariable("userId") String userId);

    @PostMapping("/punishments/{userId}/")
    PunishmentDto addPunishment(@RequestParam("days") long numberOfExceededDays, @PathVariable("userId") String userId);
}

