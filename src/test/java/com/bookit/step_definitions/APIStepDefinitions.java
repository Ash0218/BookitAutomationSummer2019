package com.bookit.step_definitions;

import com.bookit.pojos.Room;
import com.bookit.utilities.APIUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class APIStepDefinitions {
    private Response response;
    private String token;
    private JsonPath jsonPath;
    private ContentType contentType;
    // we can share this variable among step definitions.


    @Given("authorization token is provided for {string}")
    public void authorization_token_is_provided_for(String role) {
        // (String string) -> changed to (String role) to make the next line has "role"
        token = APIUtilities.getToken(role);
        // (role) is from previous line (inserted role as a parameter). It must be the same with the previous one.
    }

    @Given("user accepts content type as {string}")
    public void user_accepts_content_type_as(String string) {
        if (string.toLowerCase().contains("json")){
            contentType = ContentType.JSON;
        } else if (string.toLowerCase().contains("xml")){
            contentType = ContentType.XML;
        }
    }

    @When("user sends GET request to {string}")
    public void user_sends_GET_request_to(String string) {
        response = given().
                        accept(contentType).
                        auth().oauth2(token).
                    when().
                        get(string).prettyPeek();
    }

    // Then user should be able to see 18 rooms <- from GetRooms.feature
    @Then("user should be able to see {int} rooms")
    public void user_should_be_able_to_see_rooms(int expected) {
        // (Integer int1) -> changed to (int expected)
        List<?> rooms = response.jsonPath().get();
        Assert.assertEquals(expected, rooms.size());
    }

    // And user verifies that response status code is 200 <- from GetRooms.feature
    @Then("user verifies that response status code is {int}")
    public void user_verifies_that_response_status_code_is(int expected) {
        // (Integer int1) -> changed to (int expected)
        Assert.assertEquals(expected, response.statusCode());
    }
    /*
    Any number in cucumber test step becomes step definition (variable).
    By changing this number, you are not changing a context of test step.
     */


    @When("user sends POST request to {string} with following information:")
    public void user_sends_POST_request_to_with_following_information(String path, List<Map<String, String>> students) {
        for (Map<String, String> student : students) {
            response = given().
                    auth().oauth2(token).
                    queryParams(student).
                    accept(contentType).
                    when().
                    post(path);

            response.then().log().body(true);

        }
    }


    @Then("user should be able to see all room names")
    public void user_should_be_able_to_see_all_room_names() {
        List<Room> rooms = response.jsonPath().getList("", Room.class); // 1

      //  Collections.sort(rooms); // 4
        // #4 will not work bc list is Unmodifiable, so we cannot
        //  change order.

        for (Room room: rooms){ // 2
            System.out.printf(room.getName()); // 3
        }

        System.out.printf("################## AFTER SORTING ######################"); // 5
        // Create new list to overcome the issue withUnmodifiable list
        List<Room> rooms2 = new ArrayList<>(rooms); // 6
        Collections.sort(rooms2); // 7

        for (Room room: rooms2){
            System.out.printf(room.getName()); // 8
        }
    }


    @Then("user payload contains following room names:") // 9
    public void user_payload_contains_following_room_names(List<String> dataTable) { // 10
        List<String> actualRoomNames = response.jsonPath().getList("name"); // 11
        // "name" is from the parameter of Postman. "name = Harvard",
        //  "name = meru" ... etc.
        Assert.assertTrue(actualRoomNames.containsAll(dataTable)); // 12
        // assert true that the actualRoomNames contains the contents in dataTable
        // "dataTable" in #12 must be the same with "dataTable" in #10
        // "List<String>" in #11 must be the same with "List<String>" in #10
    }


    @When("user sends DELETE request to {string} to exclude student") // 13
    public void user_sends_DELETE_request_to_to_exclude_student(String string) { // 14
         response = given().
         // without response, we cannot share with other step definitions
                        accept(contentType).auth().oauth2(token).
                    when().
                        delete(string).prettyPeek(); // 18
        // use student id to delete student bc in DeleteStudent.feature,
        //  we used id to exclude student.
        // In Postman, it shows "id": 5743 -> go to DeleteStudent.feature
        //  and change "/api/students/{id}" to "/api/students/5743"
    }


    @When("user verifies that status line contains {string}") // 15
    public void user_verifies_that_status_line_contains(String string) { // 16
        Assert.assertTrue(response.statusLine().contains(string)); // 17
        // "string" in #17 must be the same from "string" in #14
    }


    @Then("user deletes previously added students")
    public void user_deletes_previously_added_students(List<Map<String, String>> students) {

        }

}
