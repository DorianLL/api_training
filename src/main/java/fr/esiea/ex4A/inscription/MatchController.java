package fr.esiea.ex4A.inscription;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class MatchController {

    private final UserRepository userRepository;

    public MatchController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/matches")
    public List<MatchData> matches(@RequestParam String userName, @RequestParam String userCountry) {
        return userRepository.userMatch(userName);
    }
}