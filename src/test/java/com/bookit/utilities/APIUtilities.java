package com.bookit.utilities; // 013020

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class APIUtilities implements Endpoints {
    private static String URI = Environment.BASE_URI;

    /**
     * Method that generates access token
     *
     * @return bearer token
     */
    public static String getToken() {
        Response response = given().
                queryParam("eamil", Environment.LEADER_USERNAME).
                queryParam("password", Environment.LEADER_PASSWORD).
                when().
                get("/sign").prettyPeek();
        return response.jsonPath().getString("accessToken");
    }

    /**
     * Method that generates access token
     *
     * @param role - type of user. allowed types: student team leader, student team member and teacher
     * @return bearer token
     */
    public static String getToken(String role) {
        String userName = "";
        String password = "";
        if (role.toLowerCase().contains("lead")) {
            userName = Environment.LEADER_USERNAME;
            password = Environment.LEADER_PASSWORD;
        } else if (role.toLowerCase().contains("teacher")) {
            userName = Environment.TEACHER_USERNAME;
            password = Environment.TEACHER_PASSWORD;
        } else if (role.toLowerCase().contains("member")) {
            userName = Environment.MEMBER_USERNAME;
            password = Environment.MEMBER_PASSWORD;
        } else {
            throw new RuntimeException("Invalid user type!");
        }
        Response response = given().
                queryParam("email", userName).
                queryParam("password", password).
                when().
                get("/sign").prettyPeek();
        return response.jsonPath().getString("accessToken");
    }


    /** #1 starts here
     * Delete user based on email and password
     *
     * @param email
     * @param password
     * @return response
     *
     * this does not delete students with same name.
     */
    public static Response deleteMe(String email, String password) {
        String token = given().
                queryParam("email", email).
                queryParam("password", password).
                when().
                get("/sign").prettyPeek().jsonPath().getString("accessToken");
        // user logs in with email and password.

        int userToDelete = given().auth().oauth2(token).
                when().
                get("/api/users/me").jsonPath().getInt("id");
        // we get user ID based on endpoint ("/api/users/me").

        Response response = given().auth().oauth2(getToken("teacher")).delete(DELETE_STUDENT, userToDelete);
        response.prettyPeek();
        // we log in as a teacher and delete the user with this user ID (userToDelete).
        // The users have different tokens.
        return response;
    }
    // #1 ends here


    /**
     * Method to find duplicates in the list of objects. Override equals method for your custom class and provide strategy of equality.
     *
     * @param list of objects to search for duplicates
     * @return true or false
     */
    public static boolean hasDuplicates(List<Object> list) {
        boolean hasDuplicates = false;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j)) && i != j) {
                    hasDuplicates = true;
                    System.out.println("Duplicate: " + list.get(i));
                    break;
                }
            }
        }
        return hasDuplicates;
    }
}