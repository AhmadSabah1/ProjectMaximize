package com.projectmaximize;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.interfaces.Project;
import com.projectmaximize.core.EmployeeImpl;
import com.projectmaximize.core.ProjectManagerImpl;
import com.projectmaximize.interfaces.Activity;
import com.projectmaximize.interfaces.Employee;

public class ProjectManagerImplTest {
    private ProjectManagerImpl projectManager;
    private String projectId;

    @Before
    public void setUp() {
        projectManager = new ProjectManagerImpl("Manager Name");
        // Invariant: The project manager must always have a valid, non-null name.
        projectManager.createProject("New Project", "A description of the new project");
        projectId = projectManager.getProjects().keySet().iterator().next();
    }

    @Test
    public void testCreateProject() {
        // Postcondition: New project should be created with specified name and description.
        Project project = projectManager.getProject(projectId);
        assertNotNull("Project should not be null", project);
        assertEquals("Project name should match", "New Project", project.getName());
        assertEquals("Project description should match", "A description of the new project", project.getDescription());
    }

    @Test
    public void testEditProject() {
        // Precondition: The project must exist to be editable.
        projectManager.editProject(projectId, "Updated Project", "Updated description");

        // Postcondition: Project details should be updated.
        Project project = projectManager.getProject(projectId);
        assertEquals("Updated project name should match", "Updated Project", project.getName());
        assertEquals("Updated project description should match", "Updated description", project.getDescription());
    }

    @Test
    public void testCreateActivity() {
        // Precondition: A valid project must exist to add an activity.
        projectManager.createActivity(projectId, "Development", 120);

        // Postcondition: Activity should be created with the specified name and hours.
        Project project = projectManager.getProject(projectId);
        assertFalse("Project activities should not be empty", project.getActivities().isEmpty());
        Activity activity = project.getActivities().get(0);
        assertEquals("Activity name should match", "Development", activity.getName());
        assertEquals("Estimated hours should match", 120, activity.getEstimatedHours());
    }

    @Test
    public void testAllocateResource() {
        // Preconditions: Must have valid project and activity to assign a resource.
        Employee employee = new EmployeeImpl("Jane Doe");
        projectManager.createActivity(projectId, "Development", 120);
        Activity activity = projectManager.getProject(projectId).getActivities().get(0);

        // Postcondition: Employee should be allocated to the activity.
        projectManager.allocateResource(activity.getId(), employee);
        assertTrue("Employee should be listed as assigned to the activity", activity.getAssignedEmployees().contains(employee));
    }

    @Test
    public void testGetProjectProgress() {
        // Setup: Register full hours to match estimated to achieve 100% progress.
        projectManager.createActivity(projectId, "Development", 120);
        Activity activity = projectManager.getProject(projectId).getActivities().get(0);
        activity.registerTime(120);

        // Postcondition: Project progress should report as 100%.
        String progress = projectManager.getProjectProgress(projectId);
        assertEquals("Progress should be 100%", "Progress: 100%", progress);
    }

    @Test
    public void testGetTotalTimeConsumption() {
        // Setup: Log hours in an activity.
        projectManager.createActivity(projectId, "Development", 120);
        Activity activity = projectManager.getProject(projectId).getActivities().get(0);
        activity.registerTime(60);
        activity.registerTime(60);

        // Postcondition: Total time consumption should sum to all logged hours.
        int totalTime = projectManager.getTotalTimeConsumption(projectId);
        assertEquals("Total time consumption should match logged hours", 120, totalTime);
    }
}
