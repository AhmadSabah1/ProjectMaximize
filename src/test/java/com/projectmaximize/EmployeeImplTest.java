package com.projectmaximize;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.interfaces.Activity;

public class EmployeeImplTest {
    private EmployeeImpl employee;
    private Activity activityMock;

    @Before
    public void setUp() {
        employee = new EmployeeImpl("John Doe");
        activityMock = new ActivityImpl("Development", 120);
    }

    @Test
    public void testEmployeeCreation() {
        assertEquals("John Doe", employee.getName());
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
        employee.logHours(activityMock, 5);
        assertEquals(5, employee.getTotalHoursLogged());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLogNegativeHours() {
        employee.logHours(activityMock, -1);
    }

    @Test
    public void testMultipleLogsIncreaseHours() {
        employee.logHours(activityMock, 5);
        employee.logHours(activityMock, 3);
        assertEquals(8, employee.getTotalHoursLogged());
    }
    
    @Test
    public void testGetId() {
        assertNotNull(employee.getId());
    }
}
