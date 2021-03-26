package fr.esiea.ex4A.inscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public final List<UserData> userData;
    public final Map<UserData, Integer> dataMap;
    
    public UserRepository() {
        userData = new ArrayList<>();
        this.dataMap = new HashMap<>();
        userData.add(new UserData("mami@gmail.com", "mami", "mamimamai", "FR", "F", "F"));
        userData.add(new UserData("papi@gmail.com", "papi", "papipapai", "FR", "M", "M"));
        userData.add(new UserData("tati@gmail.com", "tati", "tatitatai", "FR", "F", "M"));
        userData.add(new UserData("sister@gmail.com", "sister", "sistersistai", "FR", "F", "F"));
    }

    public UserData getUserByName(String name) {
        return userData.stream().filter(UserData -> UserData.name.equals(name)).findAny().orElse(null);
    }

    public void addUser(UserData UserData) {
        userData.add(UserData);
    }

    public int getUserAge(UserData userData) {
        return dataMap.get(userData);
    }

    public List<UserData> userMatch(UserData currentUserInfo) {
        return userData.stream().filter(userInfo -> Math.abs(getUserAge(currentUserInfo) - getUserAge(userInfo)) <= 2
            && currentUserInfo != userInfo && currentUserInfo.sexPref.equals(userInfo.sex)
            && currentUserInfo.sex.equals(userInfo.sexPref)).collect(Collectors.toList());
    }
}
