package pl.sointeractive.interview.facebook.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Post {

    private String id;
    private String message;

}
