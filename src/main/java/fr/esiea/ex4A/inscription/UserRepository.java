package fr.esiea.ex4A.inscription;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {
    public final List<UserData> userData;

    public UserRepository() {
        userData = new ArrayList<>();
        userData.add(new UserData("toto@toto.fr", "toto", "totolatomate", "FR", "M", "F"));
        userData.add(new UserData("titi@titi.fr", "titi", "titille", "FR", "M", "M"));
        userData.add(new UserData("tata@tata.fr", "tata", "tatache", "FR", "F", "M"));
        userData.add(new UserData("tete@tete.fr", "tete", "teteau", "FR", "F", "F"));
    }

    public UserData getUserByName(String name) {
        return userData.stream().filter(UserData -> UserData.userName.equals(name)).findAny().orElse(null);
    }

    public void addUser(UserData UserData) {
        userData.add(UserData);
    }

    public List<MatchData> userMatch(String name) {
        List<MatchData> MatchDatas = new ArrayList<>();
        UserData user = getUserByName(name);
        List<UserData> userDataFiltered = userData.stream().filter(UserData -> UserData.userSexPref.equals(user.userSexPref) && !UserData.userName.equals(user.userName)).collect(Collectors.toList());
        userDataFiltered.forEach(UserData -> MatchDatas.add(new MatchData(UserData.userName, UserData.userTweeter)));
        return MatchDatas;
    }

}