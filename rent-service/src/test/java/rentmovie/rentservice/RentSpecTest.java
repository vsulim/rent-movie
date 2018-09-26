package rentmovie.rentservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rentmovie.rentservice.domain.RentConfiguration;
import rentmovie.rentservice.domain.RentFacade;
import rentmovie.rentservice.domain.RentManager;
import rentmovie.rentservice.dto.PostRentDto;
import rentmovie.rentservice.dto.RentDto;
import rentmovie.rentservice.proxy.MovieProxy;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class RentSpecTest {

    @InjectMocks
    private RentFacade rentFacade = new RentConfiguration().rentFacade();

    @InjectMocks
    private RentManager rentManager = rentFacade.getRentManager();

    @Mock
    private MovieProxy movieProxy;


    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void shouldProcessRentToDatabase() {

        PostRentDto postRentDto = createPostRentDto("1ID", "2ID", "3ID", "month");

        when(movieProxy.retrieveMoviePrice(postRentDto.getMovieId())).thenReturn(new BigDecimal(15));

        rentFacade.processRent(postRentDto);
        verify(movieProxy, times(1)).retrieveMoviePrice("3ID");

        RentDto rentDto = rentFacade.getRent("1ID");

        assertNotNull(rentDto);
        assertEquals(BigDecimal.valueOf(45), rentDto.getRentTotalPrice()); // 45 cause of logic based on period
    }

    @Test
    public void shouldRetrieveAllRentedMovies() {

    }

    private static PostRentDto createPostRentDto(String id, String userId, String moveId, String rentPeriod){
        return PostRentDto.builder()
                .id(id)
                .movieId(moveId)
                .userId(userId)
                .rentPeriod(rentPeriod)
                .build();
    }
}
