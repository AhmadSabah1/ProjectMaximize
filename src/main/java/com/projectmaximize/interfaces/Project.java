package com.projectmaximize.interfaces;

import java.util.List;

import com.projectmaximize.enums.ProjectStatus;

public interface Project {
    void addActivity(Activity activity);
    List<Activity> getActivities();
    int getTotalTimeConsumption();
    String getName();
    String getDescription();
    String getId();
    void editProjectDetails(String newName, String newDescription);
    ProjectStatus getStatus();
    void setStatus(ProjectStatus status);
    public String getProgress();
    public String getProjectId();
}