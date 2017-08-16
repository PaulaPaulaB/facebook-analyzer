package pl.sointeractive.interview.facebook.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class Facebook {

    private String id;
    private Long birthday;
    private String firstname;
    private String lastname;
    private String occupation;
    private Gender gender;
    private City city;
    private String work;
    @JsonProperty("friends")
    private List<String> friendsId;
    private String school;
    private String location;
    private Relationship relationship;
    private List<Post> posts;

}
