package rentmovie.userservice;

import org.junit.Before;
import org.junit.Test;
import rentmovie.userservice.domain.UserFacade;
import rentmovie.userservice.domain.User;
import rentmovie.userservice.domain.UserConfiguration;
import rentmovie.userservice.dto.PostUserDto;
import rentmovie.userservice.dto.UserDto;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserSpecTest {

    private UserFacade userFacade = new UserConfiguration().userFacade();

    @Before
    public void setup(){
    }

    @Test
    public void shouldCreateUser(){

        PostUserDto postUserDto = PostUserDto.builder()
                .id("1")
                .username("user")
                .email("email@gmail.pl")
                .password("password")
                .build();

        PostUserDto postUserDto2 = PostUserDto.builder()
                .id("2")
                .username("user2")
                .email("email2@gmail.pl")
                .password("password2")
                .build();

        userFacade.addUser(postUserDto);
        userFacade.addUser(postUserDto2);

        assertEquals(2, userFacade.findAllUsers().size());
    }

    @Test
    public void shouldAddPunishmentId(){

        PostUserDto postUserDto = PostUserDto.builder()
                .id("1")
                .username("user")
                .email("email@gmail.pl")
                .password("password")
                .build();

        UserDto user = userFacade.addUser(postUserDto);

        userFacade.addUserPunishment(postUserDto.getId(), "ExampleID");
        userFacade.addUserPunishment(postUserDto.getId(), "ExampleID2");

        UserDto processedUser = userFacade.findUserById(user.getId());

        assertTrue(processedUser.getPunishmentsId().contains("ExampleID"));
        assertTrue(processedUser.getPunishmentsId().contains("ExampleID2"));
    }
}
