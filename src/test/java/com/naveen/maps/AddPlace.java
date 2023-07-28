package com.naveen.maps;

import com.naveen.files.MapPayload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AddPlace {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        given()
                .log()
                .all()
                .relaxedHTTPSValidation()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(MapPayload.addPlacePayload())
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)");


    }
}
