package rentmovie.rentservice.exception;

import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@NoArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RentExceptionHandler {

    @ExceptionHandler(UserRentPunishmentException.class)
    private ResponseEntity processOrderNotFoundException(UserRentPunishmentException ex) {

        String message = ex.getMessage();

        return new ResponseEntity(message, HttpStatus.NOT_ACCEPTABLE);
    }
}


