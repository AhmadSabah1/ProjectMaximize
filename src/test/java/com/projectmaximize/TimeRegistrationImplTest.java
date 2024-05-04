package com.projectmaximize;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class TimeRegistrationImplTest {
    private TimeRegistrationImpl timeRegistration;
    private UUID activityId;
    private UUID employeeId;
    private String date;

    @Before
    public void setUp() {
        timeRegistration = new TimeRegistrationImpl();
        activityId = UUID.randomUUID();
        employeeId = UUID.randomUUID();
        date = "2024-05-04";
    }

    @Test
    public void testRegisterTime() {
        timeRegistration.registerTime(activityId, employeeId, 5, date);
        assertEquals(5, timeRegistration.getRegisteredTime(activityId, employeeId, date));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterNegativeTime() {
        timeRegistration.registerTime(activityId, employeeId, -1, date);
    }

    @Test
    public void testEditTimeEntry() {
        timeRegistration.registerTime(activityId, employeeId, 5, date);
        timeRegistration.editTimeEntry(activityId, employeeId, 8, date);
        assertEquals(8, timeRegistration.getRegisteredTime(activityId, employeeId, date));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditTimeEntryWithNegativeHours() {
        timeRegistration.editTimeEntry(activityId, employeeId, -1, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditNonexistentTimeEntry() {
        timeRegistration.editTimeEntry(activityId, employeeId, 5, "2024-05-05");
    }
}
