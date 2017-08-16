package pl.sointeractive.interview.facebook.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Gender {

    @JsonProperty("female")
    FEMALE,
    @JsonProperty("male")
    MALE
}
