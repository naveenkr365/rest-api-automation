package com.naveen.library;

import com.naveen.files.LibraryPayload;
import com.naveen.files.UtilityMethods;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddGetDeleteBook {

    @Test(dataProvider = "getbook")
    public void validateAddBook(String isbn, String aisle){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given()
                .log()
                .all()
                .header("Content-Type", "application/json")
                .body(LibraryPayload.addBook(isbn, aisle))
                .when()
                .post("/Library/Addbook.php")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();

        JsonPath js = UtilityMethods.convertToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name="getbook")
    public Object[][] getBookInput(){
        return new Object[][] {{"test","111"}, {"test","222"},{"test", "333"}};
    }
}
