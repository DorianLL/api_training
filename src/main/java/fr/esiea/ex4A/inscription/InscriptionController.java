package fr.esiea.ex4A.inscription;

import org.springframework.web.bind.annotation.*;

@RestController
public class InscriptionController {

    private final UserRepository userRepository;

    public InscriptionController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/api/inscription")
    public void inscription(@RequestBody UserData userInfo) {
        userRepository.addUser(userInfo);
    }

}