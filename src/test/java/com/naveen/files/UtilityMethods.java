package com.naveen.files;

import io.restassured.path.json.JsonPath;

public class UtilityMethods {

    public static JsonPath convertToJson(String response){
        JsonPath js = new JsonPath(response);
        return js;
    }
}
