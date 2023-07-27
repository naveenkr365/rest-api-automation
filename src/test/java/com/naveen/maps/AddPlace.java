package com.naveen.maps;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class AddPlace {
    public static void main(String[] args) {

        String place = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Naveen Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        given()
                .log()
                .all()
                .relaxedHTTPSValidation()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(place)
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);


    }
}
