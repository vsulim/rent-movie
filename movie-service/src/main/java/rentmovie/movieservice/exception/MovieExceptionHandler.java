package rentmovie.movieservice.exception;

import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@NoArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MovieExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    private ResponseEntity processOrderNotFoundException(MovieNotFoundException ex){

        String message = ex.getMessage();

        return new ResponseEntity(message, HttpStatus.NOT_FOUND);
    }
}
