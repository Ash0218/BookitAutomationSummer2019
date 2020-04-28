package com.bookit.step_definitions; // 020620

import com.bookit.utilities.Environment;
import io.cucumber.java.*;
import static io.restassured.RestAssured.*;

public class Hooks {
    /*
    this hook will work only if scenario or feature has
     @api annotation.
     */
    @Before("@api")
    public void setupAPI(){
        baseURI = Environment.BASE_URI;
    }
}
