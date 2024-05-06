Feature: Assign Employees to Activities

  As a Project Manager,
  I want to assign employees to activities,
  So that I can effectively manage resource allocation and ensure tasks are properly staffed.

  Scenario: Successfully assign an employee to an activity
    Given a project named "Development Project" exists with description "Software development"
    And an activity named "Code Review" with 10 estimated hours exists in "Development Project"
    And an employee named "John Doe" exists
    When I assign "John Doe" to "Code Review" in "Development Project"
    Then "John Doe" should be listed as an assigned employee for "Code Review" in "Development Project"

  Scenario: Attempt to assign an employee to a non-existent activity
    Given a project named "Development Project" exists with description "Software development"
    And an employee named "Jane Smith" exists
    When I try to assign "Jane Smith" to a non-existent activity "Design Implementation" in "Development Project"
    Then an error message "Activity does not exist" should be displayed
