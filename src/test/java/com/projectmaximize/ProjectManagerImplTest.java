package com.projectmaximize;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.interfaces.Project;
import com.projectmaximize.interfaces.Activity;
import com.projectmaximize.interfaces.Employee;

public class ProjectManagerImplTest {
    private ProjectManagerImpl projectManager;
    private String projectId;

    @Before
    public void setUp() {
        projectManager = new ProjectManagerImpl("Manager Name");
        projectManager.createProject("New Project", "A description of the new project");
        projectId = projectManager.getProjects().keySet().iterator().next();
    }

    @Test
    public void testCreateProject() {
        Project project = projectManager.getProject(projectId);
        assertNotNull(project);
        assertEquals("New Project", project.getName());
        assertEquals("A description of the new project", project.getDescription());
    }

    @Test
    public void testEditProject() {
        projectManager.editProject(projectId, "Updated Project", "Updated description");
        Project project = projectManager.getProject(projectId);
        assertEquals("Updated Project", project.getName());
        assertEquals("Updated description", project.getDescription());
    }

    @Test
    public void testCreateActivity() {
        projectManager.createActivity(projectId, "Development", 120);
        Project project = projectManager.getProject(projectId);
        assertFalse(project.getActivities().isEmpty());
        Activity activity = project.getActivities().get(0);
        assertEquals("Development", activity.getName());
        assertEquals(120, activity.getEstimatedHours());
    }

    @Test
    public void testAllocateResource() {
        Employee employee = new EmployeeImpl("Jane Doe");
        projectManager.createActivity(projectId, "Development", 120);
        Activity activity = projectManager.getProject(projectId).getActivities().get(0);
        projectManager.allocateResource(activity.getId(), employee);
        assertTrue(activity.getAssignedEmployees().contains(employee));
    }

    @Test
    public void testGetProjectProgress() {
        projectManager.createActivity(projectId, "Development", 120);
        Activity activity = projectManager.getProject(projectId).getActivities().get(0);
        activity.registerTime(120);
        String progress = projectManager.getProjectProgress(projectId);
        assertEquals("Progress: 100%", progress);
    }

    @Test
    public void testGetTotalTimeConsumption() {
        projectManager.createActivity(projectId, "Development", 120);
        Activity activity = projectManager.getProject(projectId).getActivities().get(0);
        activity.registerTime(60);
        activity.registerTime(60);
        int totalTime = projectManager.getTotalTimeConsumption(projectId);
        assertEquals(120, totalTime);
    }
}
