package com.projectmaximize.cucumberTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.projectmaximize.cucumberTests",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true
)
public class RunCucumberTest {
}
