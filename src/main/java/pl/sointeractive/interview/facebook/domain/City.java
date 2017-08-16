package pl.sointeractive.interview.facebook.domain;

import lombok.Data;

@Data
public class City {


    private String countryName;
    private String cityName;
    private String stateName;
    private Coords coords;

    @Data
    public static class Coords {

        private Double lat;
        private Double lon;
    }
}
