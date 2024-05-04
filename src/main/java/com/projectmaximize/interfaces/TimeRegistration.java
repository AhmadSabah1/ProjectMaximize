package com.projectmaximize.interfaces;

import java.util.UUID;

public interface TimeRegistration {
    void registerTime(UUID activityId, UUID employeeId, int hoursWorked, String date);
    void editTimeEntry(UUID activityId, UUID employeeId, int hoursWorked, String date);
}
