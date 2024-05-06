Feature: Activity Creation

  As a Project Manager,
  I want to add activities to projects,
  So that tasks can be tracked and managed efficiently within projects.

  Scenario: Add a new activity to an existing project
    Given a project named "Project Maximize" exists
    When I add a new activity "Design Phase" with 120 estimated hours to "Project Maximize"
    Then "Design Phase" should be listed under activities in "Project Maximize"