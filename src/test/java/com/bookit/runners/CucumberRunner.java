package com.bookit.runners; // 020620

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/bookit/step_definitions",
        dryRun = true,
        tags = "@api"
        // run after this
)
public class CucumberRunner {
}
