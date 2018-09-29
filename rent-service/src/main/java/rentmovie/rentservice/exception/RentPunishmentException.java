package rentmovie.rentservice.exception;

import rentmovie.rentservice.dto.PunishmentDto;

import java.util.List;

public class RentPunishmentException extends RuntimeException{

    public RentPunishmentException(String message) {
        super(message);
    }
}
