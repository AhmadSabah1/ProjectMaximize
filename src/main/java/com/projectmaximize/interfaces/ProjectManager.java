package com.projectmaximize.interfaces;

public interface ProjectManager {
    void createProject(String name, String description);
    void editProject(String projectId, String newName, String newDescription);
    void createActivity(String projectId, String activityName, int estimatedHours);
    void allocateResource(String activityId, Employee employee);
    String getProjectProgress(String projectId);
    int getTotalTimeConsumption(String projectId);
    Project getProject(String projectId);
    String getName();
}
