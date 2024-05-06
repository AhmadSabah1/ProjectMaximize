package com.projectmaximize.cucumberTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.projectmaximize.EmployeeImpl;
import com.projectmaximize.ProjectManagerImpl;
import com.projectmaximize.ProjectReportGeneratorImpl;
import com.projectmaximize.interfaces.Activity;
import com.projectmaximize.interfaces.Project;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllCucumberTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ProjectManagerImpl projectManager;
    private ProjectReportGeneratorImpl reportGenerator;
    private String reportContent;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent));
        projectManager = new ProjectManagerImpl("Manager Name");
        reportGenerator = new ProjectReportGeneratorImpl(projectManager);
    }

    @After
    public void tearDown() {
        System.out.flush();
        System.err.flush();
        System.setOut(originalOut);
        System.setErr(originalOut);
        outContent.reset();
    }

    @Given("a project named {string} exists")
    public void a_project_named_exists(String projectName) {
        projectManager.createProject(projectName, "Sample project description");
    }

    @When("I add a new activity {string} with {int} estimated hours to {string}")
    public void i_add_a_new_activity_with_estimated_hours_to(String activityName, int hours, String projectName) {
        Project project = projectManager.getProjectByName(projectName);
        if (project != null) {
            projectManager.createActivity(project.getId(), activityName, hours);
        } else {
            System.out.println("Project does not exist");
        }
    }

    @Then("{string} should be listed under activities in {string}")
    public void should_be_listed_under_activities_in(String activityName, String projectName) {
        Project project = projectManager.getProjectByName(projectName);
        assertNotNull("Project should exist", project);
        boolean activityExists = project.getActivities().stream()
                .anyMatch(activity -> activity.getName().equals(activityName));
        assertTrue("Activity should be listed in the project", activityExists);
    }

    @When("I attempt to add a new activity {string} with {int} estimated hours to a non-existent project {string}")
    public void i_attempt_to_add_a_new_activity_with_estimated_hours_to_a_non_existent_project(String activityName,
            int estimatedHours, String projectName) {
        projectManager.createActivity("nonexistentProjectId", activityName, estimatedHours);
    }

    @Then("the activity should not be added and an error message {string} should be displayed")
    public void the_activity_should_not_be_added_and_an_error_message_should_be_displayed(String errorMessage) {
        String errorMessageOutput = outContent.toString();
        assertTrue("Error message should be displayed", errorMessageOutput.contains(errorMessage));
    }

    @When("I create a new project with required details")
    public void i_create_a_new_project_with_required_details() {
        projectManager.createProject("Project Maximize", "To create a project that maximizes output!");
    }

    @Then("the new project should be created and visible in the project list")
    public void the_new_project_should_be_created_and_visible_in_the_project_list() {
        Project project = projectManager.getProjectByName("Project Maximize");
        assertNotNull("Project should be created", project);
        assertTrue("Project should be in the list", projectManager.getProjects().containsKey(project.getId()));
        assertTrue("Project created successfully should be printed",
                outContent.toString().contains("Project created successfully"));
    }

    @When("I attempt to create a new project without providing all required details")
    public void i_attempt_to_create_a_new_project_without_providing_all_required_details() {
        projectManager.createProject("", "");
    }

    @Then("the project should not be created and an error message {string} should be displayed")
    public void the_project_should_not_be_created_and_an_error_message_should_be_displayed(String errorMessage) {
        String errorMessageOutput = outContent.toString();
        assertTrue("Error message should be displayed", errorMessageOutput.contains(errorMessage));
    }

    @Given("a project named {string} with the description {string} exists")
    public void a_project_named_with_the_description_exists(String name, String description) {
        projectManager.createProject(name, description);
    }

    @When("I want to change the project name from {string} to {string} and the description from {string} to {string}")
    public void i_want_to_change_the_project_name_from_to_and_the_description_from_to(String oldName, String newName,
            String oldDescription, String newDescription) {
        Project project = projectManager.getProjectByName(oldName);
        assertNotNull("Project should exist before renaming", project);
        projectManager.editProject(project.getId(), newName, newDescription);
    }

    @Then("the project details should reflect {string} and {string}")
    public void the_project_details_should_reflect_and(String expectedName, String expectedDescription) {
        Project project = projectManager.getProjectByName(expectedName);
        assertNotNull(project);
        assertEquals(expectedName, project.getName());
        assertEquals(expectedDescription, project.getDescription());
    }

    @When("I attempt to change the details of a non-existent project")
    public void i_attempt_to_change_the_details_of_a_non_existent_project() {
        projectManager.editProject("nonExistentId", "Nonexistent Name", "No Description");
    }

    @Then("an error message {string} should be displayed for non existing project")
    public void an_error_message_should_be_displayed_non_existing_project(String errorMessage) {
        assertTrue(outContent.toString().contains(errorMessage));
    }

    @Given("a project named {string} exists with description {string} 1")
    public void a_project_named_exists_with_description1(String projectName, String description) {
        projectManager.createProject(projectName, description);
    }

    @Given("an activity named {string} with {int} estimated hours exists in {string}")
    public void an_activity_named_with_estimated_hours_exists_in(String activityName, int hours, String projectName) {
        Project project = projectManager.getProjectByName(projectName);
        projectManager.createActivity(project.getId(), activityName, hours);
    }

    @Given("an employee named {string} exists")
    public void an_employee_named_exists(String employeeName) {
    }

    @When("I assign {string} to {string} in {string}")
    public void i_assign_to_in(String employeeName, String activityName, String projectName) {
        Project project = projectManager.getProjectByName(projectName);
        Activity activity = project.getActivities().stream()
                .filter(a -> a.getName().equals(activityName))
                .findFirst()
                .orElse(null);
        if (activity != null) {
            EmployeeImpl employee = new EmployeeImpl(employeeName);

            projectManager.allocateResource(activity.getId(), employee);
        } else {
            System.out.println("Activity does not exist");
        }
    }

    @Then("{string} should be listed as an assigned employee for {string} in {string}")
    public void should_be_listed_as_an_assigned_employee_for_in(String employeeName, String activityName,
            String projectName) {
        Activity activity = projectManager.getProjectByName(projectName).getActivities().stream()
                .filter(a -> a.getName().equals(activityName))
                .findFirst()
                .orElse(null);
        assertNotNull("Activity should exist", activity);
        assertTrue("Employee should be assigned to activity",
                activity.getAssignedEmployees().stream().anyMatch(e -> e.getName().equals(employeeName)));
    }

    @When("I try to assign {string} to a non-existent activity {string} in {string}")
    public void i_try_to_assign_to_a_non_existent_activity_in(String employeeName, String activityName,
            String projectName) {
        projectManager.allocateResource("nonExistentActivityId", new EmployeeImpl(employeeName));
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String errorMessage) {
        assertTrue("Error message should be displayed", outContent.toString().contains(errorMessage));
    }

    @Given("a project named {string} exists with description {string}")
    public void a_project_named_exists_with_description(String projectName, String description) {
        projectManager.createProject(projectName, description);
    }

    @Given("the project {string} has activities with recorded hours")
    public void the_project_has_activities_with_recorded_hours(String projectName) {
        Project project = projectManager.getProjectByName(projectName);
        projectManager.createActivity(project.getId(), "Design", 10);
        projectManager.createActivity(project.getId(), "Development", 20);
    }

    @Given("the project {string} has activities with assigned employees")
    public void the_project_has_activities_with_assigned_employees(String projectName) {
        Project project = projectManager.getProjectByName(projectName);
        projectManager.createActivity(project.getId(), "Design", 10);
        projectManager.createActivity(project.getId(), "Development", 20);
        Activity design = project.getActivities().get(0);
        Activity development = project.getActivities().get(1);
        EmployeeImpl employee = new EmployeeImpl("John Doe");
        design.allocateEmployee(employee);
        development.allocateEmployee(employee);
    }

    @When("I generate a time report for {string}")
    public void i_generate_a_time_report_for(String projectName) {
        Project project = projectManager.getProjectByName(projectName);
        reportContent = reportGenerator.generateTimeReport(project.getId());
    }

    @When("I generate a resource allocation report for {string}")
    public void i_generate_a_resource_allocation_report_for(String projectName) {
        Project project = projectManager.getProjectByName(projectName);
        reportContent = reportGenerator.generateResourceAllocationReport(project.getId());
    }

    @Then("the report should show the total hours spent on the project")
    public void the_report_should_show_the_total_hours_spent_on_the_project() {
        assertTrue("Report should contain total hours", reportContent.contains("Total Hours:"));
    }

    @Then("the report should detail which employees are assigned to each activity")
    public void the_report_should_detail_which_employees_are_assigned_to_each_activity() {
        assertTrue("Report should contain employee assignments", reportContent.contains("Allocated Resources:"));
    }
}
