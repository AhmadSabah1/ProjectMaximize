package com.projectmaximize.cucumberTests;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.projectmaximize.App;

public class ProjectCreationSteps {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private App app;

    @Before
    public void setUp() {
        app = new App();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Given("I am on the main menu")
    public void i_am_on_the_main_menu() {
        app.main(null);
    }

    @When("I choose to create a new project")
    public void i_choose_to_create_a_new_project() {
        System.err.println("hejs");
    }

    @And("I enter {string} as the name and {string} as the description")
    public void i_enter_as_the_name_and_as_the_description(String name, String description) {
    }

    @Then("I should see {string}.")
    public void i_should_see(String expectedOutput) {
        assertTrue(outContent.toString().contains(expectedOutput));
    }
}
