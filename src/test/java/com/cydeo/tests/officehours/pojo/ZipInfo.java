package com.cydeo.tests.officehours.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class ZipInfo {
    @JsonProperty("post code")
    public String postCode;
    public String country;
    @JsonProperty("country abbreviation")
    public String countryAbbreviation;
    public List<Place> places;
}
