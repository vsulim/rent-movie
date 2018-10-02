package rentmovie.userservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rentmovie.userservice.dto.PostUserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") /** add your's*/
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody PostUserDto postUserDto) {
        userFacade.addUser(postUserDto);
    }

    @GetMapping(value="/token")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> getToken(HttpSession session, HttpServletRequest request) {
        return Collections.singletonMap("token", session.getId());
    }

    @PostMapping(value="/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    @PutMapping("/of/{userId}/punishment/add")
    @ResponseStatus(HttpStatus.OK)
    public void processAddPunishment(@PathVariable String userId, @RequestParam("punishmentId") String punishmentId) {
        userFacade.addUserPunishment(userId, punishmentId);
    }

    @PutMapping("/of/{userId}/punishment/remove")
    @ResponseStatus(HttpStatus.OK)
    public void processRemovePunishment(@PathVariable String userId, @RequestParam("punishmentId") String punishmentId) {
        userFacade.removeUserPunishment(userId, punishmentId);
    }
}
