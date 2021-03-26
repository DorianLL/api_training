package fr.esiea.ex4A.inscription;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import fr.esiea.ex4A.service.AgifyClient;
public class AgifyClientIT {

    @Test
    public void callTest() throws IOException {
        AgifyClient agifyClient = create();
        Call<ResponseBody> call = agifyClient.getUser("michael", "US");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                try {
                    JSONObject jsonObject = new JSONObject(responseBody.string());
                    assertEquals("michael", jsonObject.get("name"));
                    assertEquals(69, jsonObject.get("age"));
                    assertEquals("US", jsonObject.get("country_id"));
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                call.cancel();
            }
        });
    }

    private AgifyClient create() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.agify.io/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

        return retrofit.create(AgifyClient.class);
    }
}
