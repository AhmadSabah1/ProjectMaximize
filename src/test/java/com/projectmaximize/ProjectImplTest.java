package com.projectmaximize;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.core.ProjectImpl;
import com.projectmaximize.interfaces.Activity;
import com.projectmaximize.interfaces.Project;

public class ProjectImplTest {
    private Project project;
    private Activity activityMock;

    @Before
    public void setUp() {
        // Invariant: Project name and description must not be null or empty.
        project = new ProjectImpl("Project A", "Description of Project A");
        activityMock = mock(Activity.class);
    }

    @Test
    public void testAddActivity() {
        // Precondition: The activity added must not be null.
        project.addActivity(activityMock);
        // Postcondition: The activity list should include the added activity.
        assertTrue("The activity list should contain the added activity.", project.getActivities().contains(activityMock));
    }

    @Test
    public void testTotalTimeConsumption() {
        // Setup
        when(activityMock.getTimeConsumption()).thenReturn(50);
        project.addActivity(activityMock);

        // Postcondition: The total time consumption should sum up all activity times.
        assertEquals("Total time consumption should match the sum of all activity times.", 50, project.getTotalTimeConsumption());
    }

    @Test
    public void testTotalTimeConsumptionMultipleActivities() {
        // Setup
        Activity anotherActivityMock = mock(Activity.class);
        when(activityMock.getTimeConsumption()).thenReturn(50);
        when(anotherActivityMock.getTimeConsumption()).thenReturn(70);
        project.addActivity(activityMock);
        project.addActivity(anotherActivityMock);

        // Postcondition: The total time consumption should accurately sum the time from all activities.
        assertEquals("Total time should be the sum of all activities.", 120, project.getTotalTimeConsumption());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProjectCreation() {
        // Precondition: Project name and description cannot be empty.
        new ProjectImpl("", "");
    }

    @Test
    public void testProjectWithoutActivities() {
        // Postcondition: If no activities are added, total time consumption should be 0.
        assertEquals("Total time consumption should be zero when no activities are added.", 0, project.getTotalTimeConsumption());
    }
}
