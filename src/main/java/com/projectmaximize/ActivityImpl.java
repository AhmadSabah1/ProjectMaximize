package com.projectmaximize;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projectmaximize.interfaces.Activity;
import com.projectmaximize.interfaces.Employee;

public class ActivityImpl implements Activity {
    private String id;
    private String name;
    private int estimatedHours;
    private int hoursLogged;
    private List<Employee> assignedEmployees;

    public ActivityImpl(String name, int estimatedHours) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.estimatedHours = estimatedHours;
        this.hoursLogged = 0;
        this.assignedEmployees = new ArrayList<Employee>();
    }

    @Override
    public void registerTime(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Cannot register negative hours.");
        }
        this.hoursLogged += hours;
    }

    @Override
    public int getTimeConsumption() {
        return hoursLogged;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getEstimatedHours() {
        return estimatedHours;
    }

    @Override
    public void allocateEmployee(Employee employee) {
        if (!assignedEmployees.contains(employee)) {
            assignedEmployees.add(employee);
        }
    }

    @Override
    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void editActivityDetails(String newName, int newEstimatedHours) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("New name cannot be empty or null.");
        }
        if (newEstimatedHours < 0) {
            throw new IllegalArgumentException("Estimated hours cannot be negative.");
        }
        this.name = newName;
        this.estimatedHours = newEstimatedHours;
    }
    
}