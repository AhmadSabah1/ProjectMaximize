package com.projectmaximize.core;

import com.projectmaximize.interfaces.Activity;
import com.projectmaximize.interfaces.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Max
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
    public void logHours(String activityId, int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative.");
        }
    
        Activity activity = getActivityById(activityId);
        if (activity == null) {
            System.err.println("Activity not found with ID: " + activityId);
            throw new IllegalArgumentException("Activity not assigned to this employee");
        }
    
        List<Employee> assignedEmployees = activity.getAssignedEmployees();
        if (assignedEmployees == null || assignedEmployees.isEmpty()) {
            System.err.println("No employees assigned to this activity.");
            return;
        }

        boolean isEmployeeAssigned = assignedEmployees.stream().anyMatch(employee -> employee.getId().equals(id));
        if (isEmployeeAssigned) {
            hoursPerActivity.merge(activity, hours, Integer::sum);
            activity.registerTime(hours);
        } else {
            System.err.println("You are not assigned to this activity.");
        }
    }

    @Override
    public int getTotalHoursLogged() {
        return hoursPerActivity.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Activity> getActivities() {
        return new ArrayList<>(hoursPerActivity.keySet());
    }

    @Override
    public void printAllActivities() {
        if (hoursPerActivity.isEmpty()) {
            System.out.println(name + " has no logged activities.");
            return;
        }

        hoursPerActivity.forEach((activity, hours) -> {
            System.out.println("  Activity: " + activity.getName() + ", Hours Logged: " + hours);
        });
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void assignActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Cannot assign a null activity.");
        }
        hoursPerActivity.putIfAbsent(activity, 0);
    }

    private Activity getActivityById(String activityId) {
        for (Activity activity : hoursPerActivity.keySet()) {
            if (activity.getId().equals(activityId)) {
                return activity;
            }
        }
        return null;
    }
}
