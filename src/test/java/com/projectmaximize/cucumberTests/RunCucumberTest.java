package com.projectmaximize.cucumberTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features", // Path to the directory of feature files
    glue = "com.projectmaximize.cucumberTests", // Package where the step definitions are found
    plugin = {"pretty", "html:target/cucumber-reports"}, // Plugins for reporting
    monochrome = true // More readable console output
)
public class RunCucumberTest {
    // This class remains empty; it’s only used to configure and run the Cucumber test.
}
