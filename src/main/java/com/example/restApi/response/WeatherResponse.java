package com.example.restApi.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse{

    private Current current;

    @Getter
    @Setter
    public class Current{

        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        // @JsonProperty("feelslike")
        private int feelslike;
    }
}

