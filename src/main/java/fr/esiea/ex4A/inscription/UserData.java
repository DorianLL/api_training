package fr.esiea.ex4A.inscription;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {

    public final String email;
    public final String name;
    public final String twitter;
    public final String country;
    public final String sex;
    public final String sexPref;

    public UserData(@JsonProperty("userEmail") String userEmail, @JsonProperty("userName") String userName, @JsonProperty("userTweeter") String userTweeter, @JsonProperty("userCountry") String userCountry,
                    @JsonProperty("userSex") String userSex, @JsonProperty("userSexPref") String userSexPref) {
        this.email = userEmail;
        this.name = userName;
        this.twitter = userTweeter;
        this.country = userCountry;
        this.sex = userSex;
        this.sexPref = userSexPref;
    }

    @Override
    public String toString() {
        return (
            "{" +
                "email: " + email + "," +
                "name: " + name + "," +
                "twitter: " + twitter + "," +
                "country: " + country + "," +
                "sex: " + sex + "," +
                "sexPref: " + sexPref + "," +
            "}"
        );
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((UserData) obj).name) && country.equals(((UserData) obj).country);
    }
}