package rentmovie.punishmentservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserProxy {

    @PutMapping("/users/of/{userId}/punishment/add/")
    void addUserPunishment(@PathVariable("userId") String userId, @RequestParam("punishmentId") String punishmentId);

    @PutMapping("/users/of/{userId}/punishment/remove")
    void removeUserPunishment(@PathVariable("userId") String userId, @RequestParam("punishmentId") String punishmentId);
}
