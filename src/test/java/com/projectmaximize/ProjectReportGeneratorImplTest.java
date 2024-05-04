package com.projectmaximize;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.interfaces.Project;
import com.projectmaximize.interfaces.ProjectManager;
import com.projectmaximize.interfaces.Activity;

import java.util.Arrays;
import java.util.UUID;

public class ProjectReportGeneratorImplTest {
    private ProjectReportGeneratorImpl reportGenerator;
    private ProjectManager projectManagerMock;
    private Project projectMock;
    private Activity activityMock;
    private UUID projectId;

    @Before
    public void setUp() {
        projectManagerMock = mock(ProjectManager.class);
        projectMock = mock(Project.class);
        activityMock = mock(Activity.class);
        projectId = UUID.randomUUID();
        reportGenerator = new ProjectReportGeneratorImpl(projectManagerMock);

        when(projectManagerMock.getProject(projectId.toString())).thenReturn(projectMock);
        when(projectMock.getActivities()).thenReturn(Arrays.asList(activityMock));
        when(activityMock.getName()).thenReturn("Development");
        when(activityMock.getTimeConsumption()).thenReturn(100);
    }

    @Test
    public void testGenerateTimeReport() {
        String expectedReport = "Time Report for Project: Project A\nDevelopment - Total Hours: 100\n";
        when(projectMock.getName()).thenReturn("Project A");

        assertEquals(expectedReport, reportGenerator.generateTimeReport(projectId));
    }

    @Test
    public void testGenerateResourceAllocationReportNotFound() {
        when(projectManagerMock.getProject(projectId.toString())).thenReturn(null);
        
        String expectedReport = "Project not found.";
        assertEquals(expectedReport, reportGenerator.generateResourceAllocationReport(projectId));
    }

    @Test
    public void testGenerateTimeReportNotFound() {
        when(projectManagerMock.getProject(projectId.toString())).thenReturn(null);
        
        String expectedReport = "Project not found.";
        assertEquals(expectedReport, reportGenerator.generateTimeReport(projectId));
    }

    @Test
    public void testGenerateResourceAllocationReport() {
        when(projectMock.getName()).thenReturn("Project A");
        when(activityMock.getAssignedEmployees()).thenReturn(Arrays.asList(
            new EmployeeImpl("John Doe")
        ));

        String expectedReport = "Resource Allocation Report for Project: Project A\nDevelopment - Allocated Resources:\n   - John Doe\n";
        assertEquals(expectedReport, reportGenerator.generateResourceAllocationReport(projectId));
    }
}
