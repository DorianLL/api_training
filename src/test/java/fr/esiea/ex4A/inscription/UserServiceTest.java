package fr.esiea.ex4A.inscription;


import fr.esiea.ex4A.service.AgifyClient;
import fr.esiea.ex4A.service.User;
import fr.esiea.ex4A.service.UserService;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.Calls;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    public void registerUserTest() throws IOException {
        AgifyClient agifyClient = mock(AgifyClient.class);
        String responseString = "{\"name\":\"test\",\"age\":20,\"count\":1000,\"country_id\":\"FR\"}";
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json"), responseString);
        Response<ResponseBody> response = Response.success(responseBody);
        Call<ResponseBody> call = Calls.response(response);
        when(agifyClient.getUser(any(), any())).thenReturn(call);

        UserData userData = new UserData("test@test.fr", "test", "test", "FR", "O", "O");
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository, agifyClient);

        assertEquals(0, userRepository.userData.size());
        userService.registerUser(userData);
        assertEquals(1, userRepository.userData.size());
        userService.registerUser(userData);
        assertEquals(1, userRepository.userData.size());

        verify(agifyClient).getUser("test", "FR");
    }

    @Test
    public void matchesTest() {
        AgifyClient agifyClient = mock(AgifyClient.class);
        UserData userData = new UserData("test@test.fr", "test", "test", "FR", "M", "O");
        UserData userData2 = new UserData("test2@test.fr", "test2", "test2", "FR", "O", "M");
        UserData userData3 = new UserData("test3@test.fr", "test3", "test3", "FR", "O", "F");
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository, agifyClient);

        userRepository.addUser(userData);
        userRepository.addUser(userData2);
        userRepository.addUser(userData3);
        userRepository.dataMap.put(userData, 25);
        userRepository.dataMap.put(userData2, 29);
        userRepository.dataMap.put(userData3, 26);

        List<UserData> userMatches = userService.matches("test", "FR");
        assertEquals(1, userMatches.size());

        userMatches.clear();
        userMatches = userService.matches("test2", "FR");
        assertEquals(1, userMatches.size());

        userMatches.clear();
        userMatches = userService.matches("test3", "FR");
        assertEquals(0, userMatches.size());
    }

    @Test
    public void getUserClientTest() throws IOException {
        AgifyClient agifyClient = mock(AgifyClient.class);
        String responseString = "{\"name\":\"test\",\"age\":20,\"count\":1000,\"country_id\":\"FR\"}";
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json"), responseString);
        Response<ResponseBody> response = Response.success(responseBody);
        Call<ResponseBody> call = Calls.response(response);
        when(agifyClient.getUser(any(), any())).thenReturn(call);

        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository, agifyClient);

        User userClient = userService.getUserClient("test", "FR");
        assertEquals("test", userClient.name);
        assertEquals(20, userClient.age);
        assertEquals(1000, userClient.count);
        assertEquals("FR", userClient.country_id);

        verify(agifyClient).getUser("test", "FR");
    }

}
