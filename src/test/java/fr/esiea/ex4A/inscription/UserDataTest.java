package fr.esiea.ex4A.inscription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class UserDataTest {

    @Test
    public void userToStringTest() {
        String name = "test";
        String email = "test@test.fr";
        String twitter = "test";
        String country = "FR";
        String sex = "M";
        String sexPref = "O";
        UserData userInfo = new UserData(email, name, twitter, country, sex, sexPref);
        String expectedString = "{" +
            "email: " + email + "," +
            "name: " + name + "," +
            "twitter: " + twitter + "," +
            "country: " + country + "," +
            "sex: " + sex + "," +
            "sexPref: " + sexPref + "," +
            "}";
        assertEquals(expectedString, userInfo.toString());
    }

    @Test
    public void userEqualsTest() {
        UserData userInfo = new UserData("test@test.fr", "test", "test", "FR", "M", "O");
        UserData userInfo2 = new UserData("test@test.fr", "test", "test", "FR", "M", "O");
        UserData userInfo3 = new UserData("test@test.fr", "test2", "test", "FR", "M", "O");
        UserData userInfo4 = new UserData("test@test.fr", "test", "test", "US", "M", "O");

        assertEquals(userInfo, userInfo2);
        assertNotEquals(userInfo, userInfo3);
        assertNotEquals(userInfo, userInfo4);
    }

}
