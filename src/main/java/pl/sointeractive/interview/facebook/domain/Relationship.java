package pl.sointeractive.interview.facebook.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Relationship {
    @JsonProperty("Married")
    MARRIED,
    @JsonProperty("Single")
    SINGLE,
}
