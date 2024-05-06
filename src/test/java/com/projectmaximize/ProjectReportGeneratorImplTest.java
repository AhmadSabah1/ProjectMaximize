package com.projectmaximize;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.interfaces.Project;
import com.projectmaximize.interfaces.ProjectManager;
import com.projectmaximize.core.EmployeeImpl;
import com.projectmaximize.core.ProjectReportGeneratorImpl;
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
        // Precondition: Project must exist to generate a report.
        String expectedReport = "Time Report for Project: Project A\nDevelopment - Total Hours: 100\n";
        when(projectMock.getName()).thenReturn("Project A");

        // Postcondition: The report format must match the expected output.
        assertEquals("Generated time report should match the expected format", expectedReport, reportGenerator.generateTimeReport(projectId.toString()));
    }

    @Test
    public void testGenerateResourceAllocationReportNotFound() {
        // Precondition: Returns null if project is not found.
        when(projectManagerMock.getProject(projectId.toString())).thenReturn(null);
        
        String expectedReport = "Project not found.";
        // Postcondition: Should return 'Project not found.' if the project is null.
        assertEquals("Report should indicate project not found when project is null", expectedReport, reportGenerator.generateResourceAllocationReport(projectId.toString()));
    }

    @Test
    public void testGenerateTimeReportNotFound() {
        // Precondition: Returns null if project is not found.
        when(projectManagerMock.getProject(projectId.toString())).thenReturn(null);
        
        String expectedReport = "Project not found.";
        // Postcondition: Should return 'Project not found.' if the project is null.
        assertEquals("Report should indicate project not found when project is null", expectedReport, reportGenerator.generateTimeReport(projectId.toString()));
    }

    @Test
    public void testGenerateResourceAllocationReport() {
        // Precondition: Project must exist and activities must have assigned employees.
        when(projectMock.getName()).thenReturn("Project A");
        when(activityMock.getAssignedEmployees()).thenReturn(Arrays.asList(
            new EmployeeImpl("John Doe")
        ));

        String expectedReport = "Resource Allocation Report for Project: Project A\nDevelopment - Allocated Resources:\n   - John Doe\n";
        // Postcondition: The report should correctly list all assigned employees for activities.
        assertEquals("Generated resource report should match the expected format", expectedReport, reportGenerator.generateResourceAllocationReport(projectId.toString()));
    }
}
