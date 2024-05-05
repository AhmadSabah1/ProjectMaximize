package com.projectmaximize;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.interfaces.Activity;

public class ActivityImplTest {
    private Activity activity;

    @Before
    public void setUp() {
        // Invariant: The activity name should not be null or empty, and estimated hours should be non-negative.
        activity = new ActivityImpl("Development Phase", 120);
    }

    @Test
    public void testActivityCreation() {
        // Postconditions: Ensure that the values set during initialization match what is retrieved.
        assertEquals("Development Phase", activity.getName());
        assertEquals(120, activity.getEstimatedHours());
    }

    @Test
    public void testRegisterTime() {
        // Precondition: Hours to be registered must be non-negative.
        int initialHours = activity.getTimeConsumption();
        activity.registerTime(50);
        // Postcondition: Check that time consumption has increased by exactly 50 hours.
        assertEquals(initialHours + 50, activity.getTimeConsumption());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterNegativeTime() {
        // Precondition: It must throw an IllegalArgumentException if the hours are negative.
        activity.registerTime(-10);
    }

    @Test
    public void testEditActivityDetails() {
        // Precondition: New name must not be null or empty, and new estimated hours must be non-negative.
        activity.editActivityDetails("Testing Phase", 150);
        // Postconditions: The activity details must be updated correctly.
        assertEquals("Testing Phase", activity.getName());
        assertEquals(150, activity.getEstimatedHours());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditActivityDetailsWithInvalidName() {
        // Precondition: Throws an exception if the new name is empty.
        activity.editActivityDetails("", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditActivityDetailsWithNegativeHours() {
        // Precondition: Throws an exception if the new estimated hours are negative.
        activity.editActivityDetails("Testing Phase", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditActivityDetailsWithNullName() {
        // Precondition: Throws an exception if the new name is null.
        activity.editActivityDetails(null, 100);
    }
}
