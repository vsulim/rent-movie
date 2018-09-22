package rentmovie.userservice.domain;

import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rentmovie.userservice.dto.PostUserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserFacade userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody PostUserDto postUserDto) {
        userService.createUser(postUserDto);
    }

    @GetMapping(value="/token")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> token(HttpSession session, HttpServletRequest request) {
            return Collections.singletonMap("token", session.getId());
    }

    @PostMapping(value="/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(){
        SecurityContextHolder.clearContext();
    }
}
