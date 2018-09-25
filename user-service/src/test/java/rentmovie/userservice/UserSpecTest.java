package rentmovie.userservice;

import org.junit.Before;
import org.junit.Test;
import rentmovie.userservice.domain.UserFacade;
import rentmovie.userservice.domain.User;
import rentmovie.userservice.domain.UserConfiguration;
import rentmovie.userservice.dto.PostUserDto;

import static org.junit.Assert.assertEquals;

public class UserSpecTest {

    private UserFacade userFacade = new UserConfiguration().accountCreator();

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

        User user1 = userFacade.addUser(postUserDto);
        User user2 = userFacade.addUser(postUserDto2);

        assertEquals(2, userFacade.getAllUsers().size());
    }
}
