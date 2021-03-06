package rentmovie.rentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PostRentDto implements Serializable {

    @Null
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private String movieId;

    @NotNull
    private String rentPeriod;

}
