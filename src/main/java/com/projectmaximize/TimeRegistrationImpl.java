package com.projectmaximize;

import com.projectmaximize.interfaces.TimeRegistration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeRegistrationImpl implements TimeRegistration {
    private Map<UUID, Map<UUID, Map<String, Integer>>> timeEntries;

    public TimeRegistrationImpl() {
        this.timeEntries = new HashMap<>();
    }

    @Override
    public void registerTime(UUID activityId, UUID employeeId, int hoursWorked, String date) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Cannot register negative hours.");
        }
        timeEntries.computeIfAbsent(activityId, k -> new HashMap<>())
                   .computeIfAbsent(employeeId, k -> new HashMap<>())
                   .merge(date, hoursWorked, Integer::sum);
    }

    @Override
    public void editTimeEntry(UUID activityId, UUID employeeId, int hoursWorked, String date) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours must be positive.");
        }
        Map<String, Integer> dateToHours = timeEntries.getOrDefault(activityId, new HashMap<>())
                                                      .getOrDefault(employeeId, new HashMap<>());
        if (!dateToHours.containsKey(date)) {
            throw new IllegalArgumentException("No such time entry exists to edit.");
        }
        dateToHours.put(date, hoursWorked);
    }

    public int getRegisteredTime(UUID activityId, UUID employeeId, String date) {
        return timeEntries.getOrDefault(activityId, new HashMap<>())
                          .getOrDefault(employeeId, new HashMap<>())
                          .getOrDefault(date, 0);
    }
}
