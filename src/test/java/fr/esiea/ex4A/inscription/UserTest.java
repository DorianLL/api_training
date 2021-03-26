package fr.esiea.ex4A.inscription;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.esiea.ex4A.service.User;

public class UserTest {

    @Test
    public void createUserClientTest() {
        User user = new User("test", 20, 1000, "FR");
        assertEquals("test", user.name);
        assertEquals(20, user.age);
        assertEquals(1000, user.count);
        assertEquals("FR", user.country_id);
    }

}