package com.cydeo.tests.officehours.day3;

import com.cydeo.tests.officehours.pojo.ZipInfo;
import com.cydeo.utils.ZipCodeTestBase;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class APITask3 extends ZipCodeTestBase {

    @Test
    public void task1() {
        //Given Accept application/json
        //And path zipcode is 22031
        //When I send a GET request to /us endpoint
        //Then status code must be 200
        //And content type must be application/json
        //And Server header is cloudflare
        //And Report-To header exists
        //And body should contains following information
        //    post code is 22031
        //    country  is United States
        //    country abbreviation is US
        //    place name is Fairfax
        //    state is Virginia
        //    latitude is 38.8604

        Response response = given().accept(ContentType.JSON)
                .pathParam("postal-code", 22031)
                .when().get("/us/{postal-code}").prettyPeek();

        assertEquals(200, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.getContentType());

        assertEquals("cloudflare", response.getHeader("Server"));

        assertFalse(response.getHeader("Report-To").isEmpty());
        assertTrue(response.getHeaders().hasHeaderWithName("Report-To"));

        ZipInfo zipInfo = response.as(ZipInfo.class);

        assertEquals("22031", zipInfo.getPostCode());

        assertEquals("United States", zipInfo.getCountry());

        assertEquals("US", zipInfo.getCountryAbbreviation());

        assertEquals("Fairfax", zipInfo.getPlaces().get(0).getPlaceName());
        assertEquals("Virginia", zipInfo.getPlaces().get(0).getState());
        assertEquals("38.8604", zipInfo.getPlaces().get(0).getLatitude());


        ZipInfo zipInfoJsonPath = response.jsonPath().getObject("", ZipInfo.class);

        assertEquals("22031", zipInfo.getPostCode());

        assertEquals("United States", zipInfo.getCountry());

        assertEquals("US", zipInfo.getCountryAbbreviation());

        assertEquals("Fairfax", zipInfo.getPlaces().get(0).getPlaceName());
        assertEquals("Virginia", zipInfo.getPlaces().get(0).getState());
        assertEquals("38.8604", zipInfo.getPlaces().get(0).getLatitude());



    }
}
