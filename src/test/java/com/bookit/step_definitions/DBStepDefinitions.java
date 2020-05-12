package com.bookit.step_definitions; // 020720

import com.bookit.db.BookitDB;
import io.cucumber.java.en.Given;
import org.junit.Assert;

public class DBStepDefinitions {

    BookitDB db = new BookitDB(); // 3

    @Given("user verifies that user with id {int} has email {string}") // 1
    public void user_verifies_that_user_with_id_has_email(Integer int1, String string) { // 2
        Assert.assertEquals(string, db.getUserEmailById(int1)); // 4
    }

}
