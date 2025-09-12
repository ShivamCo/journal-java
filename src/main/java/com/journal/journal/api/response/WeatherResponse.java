package com.journal.journal.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class WeatherResponse {

    private Request request;
    private Current current;



    @Getter
    @Setter
    public class Current{
        private String observation_time;
        private int temperature;




        @JsonProperty("ari_quality")


        private String wind_dir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;

        private int visibility;

    }



    public class Request{
        private String type;
        private String query;
        private String language;
        private String unit;
    }



}
