package fr.esiea.ex4A.inscription;


import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchData {

    public final String name;
    public final String twitter;

    public MatchData(@JsonProperty("name") String name,
                     @JsonProperty("twitter") String twitter) {
        this.name = name;
        this.twitter = twitter;
    }

}