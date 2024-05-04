package com.projectmaximize;

import com.projectmaximize.interfaces.Activity;
import com.projectmaximize.interfaces.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeImpl implements Employee {
    private String id;
    private String name;
    private Map<Activity, Integer> hoursPerActivity;

    public EmployeeImpl(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
        this.hoursPerActivity = new HashMap<>();
        this.id = java.util.UUID.randomUUID().toString();
    }

    @Override
    public void logHours(Activity activity, int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative.");
        }
        hoursPerActivity.merge(activity, hours, Integer::sum);
    }

    @Override
    public int getTotalHoursLogged() {
        return hoursPerActivity.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
