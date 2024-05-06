package com.projectmaximize;

import com.projectmaximize.interfaces.*;

import java.util.HashMap;
import java.util.Map;

public class ProjectManagerImpl implements ProjectManager {
    private String name;
    private Map<String, ProjectImpl> projects;

    public ProjectManagerImpl(String name) {
        this.name = name;
        this.projects = new HashMap<>();
    }

    @Override
    public void createProject(String name, String description) {
        if(name.isEmpty() || description.isEmpty())
        {
            System.err.println("Project name and description are required");
        } else {
            ProjectImpl project = new ProjectImpl(name, description);
            projects.put(project.getId(), project);
            System.out.println("Project created successfully.");    
        }
    }

    @Override
    public void editProject(String projectId, String newName, String newDescription) {
        ProjectImpl project = projects.get(projectId);
        if (project != null) {
            project.editProjectDetails(newName, newDescription);
        } else {
            System.out.println("Project not found");
        }
    }

    @Override
    public void createActivity(String projectId, String activityName, int estimatedHours) {
        ProjectImpl project = projects.get(projectId);
        if (project == null) {
            System.out.println("Project does not exist");
        } else {
            Activity newActivity = new ActivityImpl(activityName, estimatedHours);
            project.addActivity(newActivity);
            System.out.println("Activity created successfully!");
        }
    }

    @Override
    public void allocateResource(String activityId, Employee employee) {
        boolean hasFoundActivity = false;
        for (ProjectImpl project : projects.values()) {
            for (Activity activity : project.getActivities()) {
                if (activity.getId().equals(activityId)) {
                    activity.allocateEmployee(employee);
                    employee.assignActivity(activity);
                    hasFoundActivity = true;
                    break;
                }
            }
        }

        if(!hasFoundActivity) {
            System.out.println("Activity does not exist");
        }
    }

    @Override
    public String getProjectProgress(String projectId) {
        ProjectImpl project = projects.get(projectId);
        return (project != null) ? project.getProgress() : "Project not found";
    }

    @Override
    public int getTotalTimeConsumption(String projectId) {
        ProjectImpl project = projects.get(projectId);
        return (project != null) ? project.getTotalTimeConsumption() : 0;
    }

    @Override
    public Project getProject(String projectId) {
        return projects.get(projectId);
    }

    public Map<String, ProjectImpl> getProjects() {
        return projects;
    }

    public void displayAllProjects() {
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
            return;
        }
        System.out.println("Projects managed by " + name + ":");
        for (Map.Entry<String, ProjectImpl> entry : projects.entrySet()) {
            System.out.println("Project ID: " + entry.getKey() + ", Project Name: " + entry.getValue().getName());
        }
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public Project getProjectByName(String projectName) {
        for (ProjectImpl project : projects.values()) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        return null;
    }
}
