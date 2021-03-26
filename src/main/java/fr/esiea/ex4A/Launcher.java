package fr.esiea.ex4A;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import fr.esiea.ex4A.service.AgifyClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootApplication
public class Launcher {
    @Bean
    AgifyClient agifyClient() {
        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .build();
    
        return retrofit.create(AgifyClient.class);
    }    
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

    
}
