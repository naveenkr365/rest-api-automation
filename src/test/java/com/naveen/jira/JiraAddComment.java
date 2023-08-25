package com.naveen.jira;

import com.naveen.files.JiraPayload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;

public class JiraAddComment {

    public static void main(String[] args) {

        SessionFilter session = new SessionFilter();
        RestAssured.baseURI = "http://localhost:8080";

        //Login API
        String response = given()
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .body(JiraPayload.loginPayload())
                .when()
                .post("/rest/auth/1/session")
                .then()
                .extract().response().asString();
        System.out.println(response);
/*
        //AddComment API
        given()
                .pathParam("Id", "10002")
                .log()
                .all()
                .header("Content-Type", "application/json")
                .body(JiraPayload.addCommentPayload("RestAssured Comment1"))
                .filter(session)
                .when()
                .post("/rest/api/2/issue/{Id}/comment")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(201);
    */


    }
}
