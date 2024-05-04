package com.projectmaximize;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.projectmaximize.interfaces.Activity;

public class ActivityImplTest {
    private Activity activity;

    @Before
    public void setUp() {
        activity = new ActivityImpl("Development Phase", 120);
    }

    @Test
    public void testActivityCreation() {
        assertEquals("Development Phase", activity.getName());
        assertEquals(120, activity.getEstimatedHours());
    }

    @Test
    public void testRegisterTime() {
        activity.registerTime(50);
        assertEquals(50, activity.getTimeConsumption());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterNegativeTime() {
        activity.registerTime(-10);
    }

    @Test
    public void testEditActivityDetails() {
        activity.editActivityDetails("Testing Phase", 150);
        assertEquals("Testing Phase", activity.getName());
        assertEquals(150, activity.getEstimatedHours());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditActivityDetailsWithInvalidName() {
        activity.editActivityDetails("", 100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditActivityDetailsWithNegativeHours() {
        activity.editActivityDetails("Testing Phase", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEditActivityDetailsWithNullName() {
        activity.editActivityDetails(null, 100);
    }
}
