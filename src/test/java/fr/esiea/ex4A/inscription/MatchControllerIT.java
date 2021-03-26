package fr.esiea.ex4A.inscription;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import fr.esiea.ex4A.service.UserService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MatchControllerIT {

    private final MockMvc mockMvc;

    @MockBean
    private UserService userService;

    public MatchControllerIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void matchesThenReturnsListOfUsers() throws Exception {

        UserData UserData = new UserData("test@test.fr", "michael", "michael", "FR", "M", "F");
        UserData UserData2 = new UserData("test2@test.fr", "jane", "jane", "FR", "F", "M");
        UserData UserData3 = new UserData("test3@test.fr", "matthew", "matthew", "FR", "M", "F");

        userService.registerUser(UserData);
        userService.registerUser(UserData2);
        userService.registerUser(UserData3);

        when(userService.matches("michael", "FR")).thenReturn(List.of(UserData2));

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/matches?userName=michael&userCountry=FR"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("jane"))
            .andExpect(jsonPath("$[0].twitter").value("jane"));

        verify(userService).matches("michael", "FR");

    }

}
