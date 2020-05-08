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
        dryRun = false,
        tags = "@delete_student and not @ignore"

        // run after this
        // 1. Changed tags="@api" to tags="@api and @create_student"
        //  after adding the new scenario, @create_student
        // 2. change dryRun = false to dryRun = true
        // 3. run
        // 4. you will see the result. Copy it. ex: @when...
        // 5. insert/ add data in APIStepDefinitions.java
        // 6. comeback to CucumberRunner.java and change dryRun = false
        // 7. run
)
public class CucumberRunner {
}
