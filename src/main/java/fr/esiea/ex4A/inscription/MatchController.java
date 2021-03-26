package fr.esiea.ex4A.inscription;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esiea.ex4A.service.UserService;

@RestController
public class MatchController {

    private final UserService userService;

    public MatchController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/matches")
    public List<UserData> matches(@RequestParam String userName, @RequestParam String userCountry) {
        return userService.matches(userName, userCountry);
    }
}