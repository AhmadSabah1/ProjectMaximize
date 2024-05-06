package com.projectmaximize;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.projectmaximize.core.EmployeeImpl;
import com.projectmaximize.interfaces.Activity;

public class EmployeeImplTest {
    private EmployeeImpl employee;
    
    @Mock
    private Activity activityMock;
    
    @Mock
    private Activity unassignedActivityMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        employee = new EmployeeImpl("John Doe");

        when(activityMock.getId()).thenReturn("activity-id");
        when(activityMock.getName()).thenReturn("Development");

        when(activityMock.getAssignedEmployees()).thenReturn(java.util.Collections.singletonList(employee));
        when(activityMock.getTimeConsumption()).thenReturn(10); // Assume some time has already been logged

        when(unassignedActivityMock.getId()).thenReturn("unassigned-activity-id");
        when(unassignedActivityMock.getName()).thenReturn("Testing");
        when(unassignedActivityMock.getAssignedEmployees()).thenReturn(java.util.Collections.emptyList()); // No employees assigned

        employee.assignActivity(activityMock);
    }

    @Test
    public void testEmployeeCreation_ValidName() {
        assertNotNull("Employee's ID must always be non-null", employee.getId());
        assertFalse("Employee's name must not be empty", employee.getName().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployeeCreationWithEmptyName() {
        new EmployeeImpl("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmployeeCreationWithNullName() {
        new EmployeeImpl(null);
    }

    @Test
    public void testLogHours() {
        // Precondition
        assertTrue("Hours must be non-negative", 5 >= 0);

        employee.logHours(activityMock.getId(), 5);

        // Postcondition
        assertEquals("Total hours must increase by logged amount", 5, employee.getTotalHoursLogged());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLogNegativeHours() {
        employee.logHours(activityMock.getId(), -1);
    }

    @Test
    public void testMultipleLogsIncreaseHours() {
        int initialHours = employee.getTotalHoursLogged();
        employee.logHours(activityMock.getId(), 5);
        employee.logHours(activityMock.getId(), 3);

        // Postcondition
        assertEquals("Total hours should be sum of all logged hours", initialHours + 5 + 3, employee.getTotalHoursLogged());
    }

    @Test
    public void testLogHoursForUnassignedActivity() {
        try {
            employee.logHours(unassignedActivityMock.getId(), 1);
            fail("Should throw IllegalArgumentException because activity is not assigned to employee");
        } catch (IllegalArgumentException e) {
            // Expected exception for violating precondition
        }
    }

    @Test
    public void testAssignActivity_ValidActivity() {
        int initialNumActivities = employee.getActivities().size();
        employee.assignActivity(unassignedActivityMock);

        // Postcondition
        assertTrue("Assigned activities should contain the new activity", employee.getActivities().contains(unassignedActivityMock));
        assertEquals("Number of activities should increase by 1", initialNumActivities + 1, employee.getActivities().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssignNullActivity() {
        employee.assignActivity(null);
    }
}
