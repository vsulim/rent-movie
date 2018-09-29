package rentmovie.rentservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import rentmovie.rentservice.domain.Rent;
import rentmovie.rentservice.domain.RentConfiguration;
import rentmovie.rentservice.domain.RentFacade;
import rentmovie.rentservice.domain.RentManager;
import rentmovie.rentservice.dto.PostRentDto;
import rentmovie.rentservice.dto.PunishmentDto;
import rentmovie.rentservice.dto.RentDto;
import rentmovie.rentservice.exception.RentPunishmentException;
import rentmovie.rentservice.proxy.MovieProxy;
import rentmovie.rentservice.proxy.PunishmentProxy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
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
        when(punishmentProxy.findAnyPunishment(postRentDto.getUserId())).thenReturn(Collections.emptyList());

        rentFacade.processRent(postRentDto);
        verify(movieProxy, times(1)).retrieveMoviePrice("3ID");

        RentDto rentDto = rentFacade.getRent("1ID");

        assertNotNull(rentDto);
        assertEquals(BigDecimal.valueOf(45), rentDto.getRentTotalPrice()); // 45 cause of logic based on period
    }

    @Test(expected = RentPunishmentException.class)
    public void shouldThrowExceptionIfUserHasPunishment() {

        PostRentDto rentDto = createPostRentDto("1ID", "2ID", "3ID", "month");

        when(punishmentProxy.findAnyPunishment(rentDto.getUserId())).thenReturn(Arrays.asList(new PunishmentDto()));

        rentFacade.processRent(rentDto);
    }

    @Test
    public void shouldCallPunishmentServiceIfReturnDateExceedsPeriod(){

        Rent rent = Rent.builder()
                .id("1ID")
                .movieId("2ID")
                .userId(("3ID"))
                .rentDate(LocalDate.of(1997, 11, 1))
                .rentExpirationDate(LocalDate.of(1997, 11, 15))
                .build();

        rentManager.processReturn(rent, LocalDate.of(1997,11,25));
        verify(punishmentProxy, times(1)).addPunishmentExceededDays(isA(Long.class), isA(PunishmentDto.class));
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
