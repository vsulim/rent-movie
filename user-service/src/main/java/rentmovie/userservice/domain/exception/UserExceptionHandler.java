package rentmovie.userservice.domain.exception;

import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@NoArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity processOrderNotFoundException(UserNotFoundException ex) {

        String message = ex.getMessage();

        return new ResponseEntity(message, HttpStatus.NOT_FOUND);
    }
}
