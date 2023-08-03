package com.naveen.files;

public class LibraryPayload {

    public static String addBook(String isbn, String aisle ){
        String addBook = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"Sam Chris\"\n" +
                "}";
        return addBook;
    }
}
