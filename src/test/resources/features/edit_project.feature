Feature: Edit Project Details

  As a Project Manager,
  I want to be able to edit project details,
  So that I can correct or update project information as necessary.

  Scenario: Successfully edit an existing project's details
    Given a project named "Initial Project" with the description "Initial Description" exists
    When I want to change the project name from "Initial Project" to "Updated Project" and the description from "Initial Description" to "Updated Description"
    Then the project details should reflect "Updated Project" and "Updated Description"

  Scenario: Attempt to edit a non-existent project
    When I attempt to change the details of a non-existent project
    Then an error message "Project not found" should be displayed
