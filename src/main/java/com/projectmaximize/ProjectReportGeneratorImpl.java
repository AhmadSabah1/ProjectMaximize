package com.projectmaximize;

import com.projectmaximize.interfaces.*;

import java.util.UUID;

public class ProjectReportGeneratorImpl implements ProjectReportGenerator {
    private ProjectManager projectManager;

    public ProjectReportGeneratorImpl(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @Override
    public String generateTimeReport(String projectId) {
        Project project = projectManager.getProject(projectId);
        if (project == null) {
            return "Project not found.";
        }
        
        StringBuilder report = new StringBuilder();
        report.append("Time Report for Project: ").append(project.getName()).append("\n");
        for (Activity activity : project.getActivities()) {
            report.append(activity.getName()).append(" - Total Hours: ")
                  .append(activity.getTimeConsumption()).append("\n");
        }
        
        return report.toString();
    }

    @Override
    public String generateResourceAllocationReport(String projectId) {
        Project project = projectManager.getProject(projectId.toString());
        if (project == null) {
            return "Project not found.";
        }

        StringBuilder report = new StringBuilder();
        report.append("Resource Allocation Report for Project: ").append(project.getName()).append("\n");
        for (Activity activity : project.getActivities()) {
            report.append(activity.getName()).append(" - Allocated Resources:\n");
            for (Employee employee : activity.getAssignedEmployees()) {
                report.append("   - ").append(employee.getName()).append("\n");
            }
        }

        return report.toString();
    }
}
