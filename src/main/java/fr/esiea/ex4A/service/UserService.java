package fr.esiea.ex4A.service;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.esiea.ex4A.inscription.UserData;
import fr.esiea.ex4A.inscription.UserRepository;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final AgifyClient agifyClient;

    public UserService(UserRepository userRepository, AgifyClient agifyClient) {
        this.userRepository = userRepository;
        this.agifyClient = agifyClient;
    }

    public void registerUser(UserData UserData) throws IOException {
        if (userRepository.getUserByName(UserData.name)== null) {
            User userClient = getUserClient(UserData.name, UserData.country);
            userRepository.dataMap.put(UserData, userClient.age);
            userRepository.addUser(UserData);
        }
    }

    public List<UserData> matches(String name, String country) {
        UserData userData = userRepository.getUserByName(name);
        return userRepository.userMatch(userData);
    }

    public User getUserClient(String name, String country) throws IOException {
        Call<ResponseBody> call = agifyClient.getUser(name, country);
        Response<ResponseBody> response = call.execute();
        org.json.JSONObject jsonObject = new org.json.JSONObject(response.body().string());
        return new User(jsonObject.getString("name"), jsonObject.getInt("age"), jsonObject.getInt("count"), jsonObject.getString("country_id"));
    }

}