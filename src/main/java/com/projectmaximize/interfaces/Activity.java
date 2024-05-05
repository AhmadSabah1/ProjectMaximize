package com.projectmaximize.interfaces;

import java.util.List;

public interface Activity {
    void registerTime(int hours);
    int getTimeConsumption();
    String getName();
    int getEstimatedHours();
    void editActivityDetails(String newName, int newEstimatedHours);
    void allocateEmployee(Employee employee);
    List<Employee> getAssignedEmployees();
    String getId();
}