package rentmovie.punishmentservice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import rentmovie.punishmentservice.domain.PunishmentConfiguration;
import rentmovie.punishmentservice.domain.PunishmentFacade;
import rentmovie.punishmentservice.domain.PunishmentManager;
import rentmovie.punishmentservice.dto.PunishmentDto;
import rentmovie.punishmentservice.proxy.UserProxy;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PunishmentSpecTest {

    @InjectMocks
    private PunishmentFacade punishmentFacade = new PunishmentConfiguration().punishmentFacade();

    @Mock
    private UserProxy userProxy;

    @InjectMocks
    private PunishmentManager punishmentManager = punishmentFacade.getPunishmentManager();

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Test
    public void shouldProcessAndFindPunishmentById(){

        Mockito.doNothing().when(userProxy).addUserPunishment(isA(String.class), isA(String.class));

        PunishmentDto punishmentDto = PunishmentDto.builder()
                .id("1ID")
                .userId("2ID")
                .build();

        punishmentFacade.addPunishmentBasedOnExceededDays(punishmentDto, 12);
        PunishmentDto foundPunishment = punishmentFacade.findById(punishmentDto.getId());

        assertNotNull(foundPunishment);
        assertEquals(BigDecimal.valueOf(60),foundPunishment.getPunishmentAmount());
    }
}
