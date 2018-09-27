package rentmovie.rentservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rentmovie.rentservice.domain.Rent;
import rentmovie.rentservice.domain.RentConfiguration;
import rentmovie.rentservice.domain.RentFacade;
import rentmovie.rentservice.domain.RentManager;
import rentmovie.rentservice.dto.PostRentDto;
import rentmovie.rentservice.dto.PunishmentDto;
import rentmovie.rentservice.dto.RentDto;
import rentmovie.rentservice.exception.UserRentPunishmentException;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.proxy.PunishmentProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Mock
    private PunishmentProxy punishmentProxy;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void shouldProcessRentToDatabase() {

        PostRentDto postRentDto = createPostRentDto("1ID", "2ID", "3ID", "month");

        when(movieProxy.retrieveMoviePrice(postRentDto.getMovieId())).thenReturn(new BigDecimal(15));
        when(punishmentProxy.findAnyPunishment(postRentDto.getUserId())).thenReturn(null);

        rentFacade.processRent(postRentDto);
        verify(movieProxy, times(1)).retrieveMoviePrice("3ID");

        RentDto rentDto = rentFacade.getRent("1ID");

        assertNotNull(rentDto);
        assertEquals(BigDecimal.valueOf(45), rentDto.getRentTotalPrice()); // 45 cause of logic based on period
    }

    @Test(expected = UserRentPunishmentException.class)
    public void shouldThrowUserRentPunishmentExceptionIfUserHasPunishment() {

        PostRentDto rentDto = createPostRentDto("1ID", "2ID", "3ID", "month");

        when(punishmentProxy.findAnyPunishment(rentDto.getUserId())).thenReturn(new PunishmentDto());

        rentFacade.processRent(rentDto);
    }

    @Test
    public void shouldCallPunishmentService(){

        Rent rent = Rent.builder()
                .id("1ID")
                .movieId("2ID")
                .userId(("3ID"))
                .rentDate(LocalDate.of(1997, 11, 1))
                .rentExpirationDate(LocalDate.of(1997, 11, 14))
                .build();
    }

    private static PostRentDto createPostRentDto(String id, String userId, String moveId, String rentPeriod) {
        return PostRentDto.builder()
                .id(id)
                .movieId(moveId)
                .userId(userId)
                .rentPeriod(rentPeriod)
                .build();
    }
}
