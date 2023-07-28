package com.naveen.maps;

import com.naveen.files.MapPayload;
import com.naveen.files.UtilityMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddUpdateGetPlace {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Add Place API
        String response = given()
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
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        //Store the PlaceID values from the JSON Response
        JsonPath js1 = UtilityMethods.convertToJson(response);
        String place_id = js1.getString("place_id");


        //Update Place API
        String newAddress = "21 South East Area, India";
        String updatePlace = "{\n" +
                "\"place_id\":\""+place_id+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";

        given()
                .log()
                .all()
                .relaxedHTTPSValidation()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(updatePlace)
                .when().put("/maps/api/place/update/json")
                .then().assertThat()
                .statusCode(200)
                .body("msg",equalTo("Address successfully updated"));

        //Get Place API
        String getResponse = given()
                .log()
                .all()
                .relaxedHTTPSValidation()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
                .when().get("/maps/api/place/get/json")
                .then().assertThat()
                .statusCode(200).extract().response().asString();

        JsonPath js2 = UtilityMethods.convertToJson(getResponse);
        String actualAddress = js2.getString("address");
        Assert.assertEquals(actualAddress, newAddress);

    }
}
