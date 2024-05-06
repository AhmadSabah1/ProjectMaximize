Feature: Project Creation

  As a Project Manager,
  I want to create new projects,
  So that they can be managed and tracked within the system.

  Scenario: Create a new project with required details
    When I create a new project with required details
    Then the new project should be created and visible in the project list

  Scenario: Attempt to create a new project without providing all required details
    When I attempt to create a new project without providing all required details
    Then the project should not be created and an error message "Project name and description are required" should be displayed
