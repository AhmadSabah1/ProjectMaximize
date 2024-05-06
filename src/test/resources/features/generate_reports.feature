Feature: Generate Project Reports

  As a Project Manager,
  I want to generate reports for projects,
  So that I can review the overall progress and resource allocation efficiently.

  Scenario: Generate a time report for a project
    Given a project named "Web Development" exists with description "Development of a new web platform"
    And the project "Web Development" has activities with recorded hours
    When I generate a time report for "Web Development"
    Then the report should show the total hours spent on the project

  Scenario: Generate a resource allocation report for a project
    Given a project named "Web Development" exists with description "Development of a new web platform"
    And the project "Web Development" has activities with assigned employees
    When I generate a resource allocation report for "Web Development"
    Then the report should detail which employees are assigned to each activity
