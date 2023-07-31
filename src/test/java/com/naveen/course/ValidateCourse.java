package com.naveen.course;

import com.naveen.files.CoursePayload;
import com.naveen.files.UtilityMethods;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ValidateCourse {

    /*
    {
  "dashboard": {
    "purchaseAmount": 910,
    "website": "rahulshettyacademy.com"
  },
  "courses": [
    {
      "title": "Selenium Python",
      "price": 50,
      "copies": 6
    },
    {
      "title": "Cypress",
      "price": 40,
      "copies": 4
    },
    {
      "title": "RPA",
      "price": 45,
      "copies": 10
    }
  ]
}

1. Print No of courses returned by API
2.Print Purchase Amount
3. Print Title of the first course
4. Print All course titles and their respective Prices
5. Print no of copies sold by RPA Course
6. Verify if Sum of all Course prices matches with Purchase Amount

     */

    public static void main(String[] args) {
        //1. Print No of courses returned by API
        JsonPath js = UtilityMethods.convertToJson(CoursePayload.courseDetails());
        int courseCount = js.getInt("courses.size()");
        System.out.println("Course Count - "+courseCount);

        //2.Print Purchase Amount
        int purchaseAmt = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount - "+purchaseAmt);

        //3. Print Title of the first course
        String firstCourseTitle = js.getString("courses[0].title");
        System.out.println("First Course Title - "+firstCourseTitle);

        //4. Print All course titles and their respective Prices
        for(int i=0; i<courseCount; i++){
            String courseName = js.getString("courses["+i+"].title");
            int coursePrice = js.getInt("courses[" + i + "].price");
            System.out.println("Course Name - "+courseName+ ", Price - "+coursePrice);
        }

        //5. Print no of copies sold by RPA Course
        for(int i=0; i<courseCount;i++){
            String courseName = js.getString("courses["+i+"].title");
            if(courseName.equalsIgnoreCase("RPA")) {
                int copies = js.getInt("courses[" + i + "].copies");
                System.out.println("RPA Copies - "+copies);
                break;
            }
        }

        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for(int i=0;i<courseCount;i++){
            int price = js.getInt("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            sum = sum+price*copies;

        }
        Assert.assertEquals(sum,purchaseAmt);


    }


}
