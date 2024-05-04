package com.projectmaximize;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.interfaces.Activity;

public class ProjectImplTest {
    private ProjectImpl project;
    private Activity activityMock;

    @Before
    public void setUp() {
        project = new ProjectImpl("Project A", "Description of Project A");
        activityMock = mock(Activity.class);
    }

    @Test
    public void testAddActivity() {
        project.addActivity(activityMock);
        assertTrue(project.getActivities().contains(activityMock));
    }

    @Test
    public void testTotalTimeConsumption() {
        when(activityMock.getTimeConsumption()).thenReturn(50);
        project.addActivity(activityMock);
        assertEquals(50, project.getTotalTimeConsumption());
    }

    @Test
    public void testTotalTimeConsumptionMultipleActivities() {
        Activity anotherActivityMock = mock(Activity.class);
        when(activityMock.getTimeConsumption()).thenReturn(50);
        when(anotherActivityMock.getTimeConsumption()).thenReturn(70);
        project.addActivity(activityMock);
        project.addActivity(anotherActivityMock);
        assertEquals(120, project.getTotalTimeConsumption());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProjectCreation() {
        new ProjectImpl("", "");
    }

    @Test
    public void testProjectWithoutActivities() {
        assertEquals(0, project.getTotalTimeConsumption());
    }
}
