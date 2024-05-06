package com.projectmaximize.core;

import java.util.UUID;
import com.projectmaximize.interfaces.Activity;
import com.projectmaximize.interfaces.Project;
import com.projectmaximize.enums.*;

import java.util.ArrayList;
import java.util.List;

public class ProjectImpl implements Project {
    private String id;
    private String name;
    private String description;
    private List<Activity> activities;
    private ProjectStatus status;

    public ProjectImpl(String name, String description) {
        if (name == null || name.trim().isEmpty() || description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Project name and description must not be empty");
        }
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.activities = new ArrayList<>();
        this.status = ProjectStatus.ACTIVE;
    }

    @Override
    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    @Override
    public List<Activity> getActivities() {
        return new ArrayList<>(activities);
    }

    @Override
    public int getTotalTimeConsumption() {
        return activities.stream().mapToInt(Activity::getTimeConsumption).sum();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void editProjectDetails(String newName, String newDescription) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }
        if (newDescription != null && !newDescription.trim().isEmpty()) {
            this.description = newDescription;
        }
    }

    @Override
    public ProjectStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    @Override
    public String getProgress() {
        int totalActivities = activities.size();
        long completedActivities = activities.stream().filter(a -> ((ActivityImpl) a).getTimeConsumption() >= a.getEstimatedHours()).count();
        return String.format("Progress: %d%%", (int) ((completedActivities / (double) totalActivities) * 100));
    }

    @Override
    public String getProjectId() {
        return id;
    }
}