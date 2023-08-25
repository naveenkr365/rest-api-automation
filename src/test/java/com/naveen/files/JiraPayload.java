package com.naveen.files;

public class JiraPayload {

    public static String loginPayload(){
        String payload = "{\n" +
                "\"username\":\"naveenkr365\",\n" +
                "\"password\":\"Jira12345!\"\n" +
                "}";

        return payload;
    }

    public static String addCommentPayload(String comment){
        String payload = "{\n" +
                "    \"body\": \""+comment+"\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}";

        return payload;
    }
}
