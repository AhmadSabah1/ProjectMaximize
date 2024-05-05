package com.projectmaximize;

import com.projectmaximize.interfaces.*;

import java.util.Collections;
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
        ProjectImpl project = new ProjectImpl(name, description);
        projects.put(project.getId(), project);
    }

    @Override
    public void editProject(String projectId, String newName, String newDescription) {
        ProjectImpl project = projects.get(projectId);
        if (project != null) {
            project.editProjectDetails(newName, newDescription);
        }
    }

    @Override
    public void createActivity(String projectId, String activityName, int estimatedHours) {
        ProjectImpl project = projects.get(projectId);
        if (project != null) {
            Activity newActivity = new ActivityImpl(activityName, estimatedHours);
            project.addActivity(newActivity);
        }
    }

    @Override
    public void allocateResource(String activityId, Employee employee) {
        for (ProjectImpl project : projects.values()) {
            for (Activity activity : project.getActivities()) {
                if ((activity).getId().equals(activityId)) {
                    (activity).allocateEmployee(employee);
                    employee.assignActivity(activity);
                    break;
                }
            }
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

    @Override
    public String getName() {
        return this.name;
    }

    public Map<String, Project> getProjects() {
        return Collections.unmodifiableMap(projects);
    }
}
