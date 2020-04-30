package com.bookit.runners; // 020620

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = "com/bookit/step_definitions",
        dryRun = true,
        tags = "@api and @create_student"
        // run after this
        // 1. Changed tags="@api" to tags="@api and @create_student"
        //  after adding the new scenario, @create_student
        // 2. change dryRun = false to dryRun = true
        // 3. run
        // 4. you will see the result. Copy it. ex: @when...
)
public class CucumberRunner {
}
